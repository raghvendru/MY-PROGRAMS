Imports System
Imports System.Globalization
Imports System.Collections.Generic
Imports System.Text
Imports System.Data
Imports System.Data.Common
Imports System.Data.Common.DbException
Imports System.Data.SqlClient
Imports System.Data.SqlDbType
Imports System.Data.SqlTypes
Imports System.Configuration


Public Class DManager

    Private Shared CREATE_CONNECTION_ERROR As Integer = 101
    Private Shared CREATE_DBCOMMAND_ERROR As Integer = 102
    Private _parent As ITransaction = Nothing
    Public Sub New()

    End Sub

    Public Sub New(ByVal parent As ITransaction)
        Me._parent = parent
    End Sub

    Private Shared _factory As DbProviderFactory = Nothing

    Public Shared Function GetFactory() As DbProviderFactory
        If _factory Is Nothing Then
            Dim provider As String = ConfigurationManager.AppSettings("Provider")
            If String.IsNullOrEmpty(provider) Then Throw New ArgumentException("Failed to retrieve a valid data provider from configuration settings - entry cannot be null or empty")
            _factory = DbProviderFactories.GetFactory(provider)
        End If

        Return _factory
    End Function

    Public Shared Function CreateConnection() As DbConnection
        Dim factory As DbProviderFactory = GetFactory()
        Dim connection As DbConnection = factory.CreateConnection()
        Dim connString As String = ConfigurationManager.AppSettings("ConnectionString")
        If String.IsNullOrEmpty(connString) Then Throw New ArgumentException("Failed to retrieve a valid connection string from configuration settings - entry cannot be empty or null")
        connection.ConnectionString = connString
        Return connection
    End Function

    Public Shared Function CreateCommand() As DbCommand
        Dim factory As DbProviderFactory = GetFactory()
        Dim cmd As DbCommand = factory.CreateCommand()
        Return cmd
    End Function

    Public Shared Function CreateDataAdapter() As DbDataAdapter
        Dim factory As DbProviderFactory = GetFactory()
        Return factory.CreateDataAdapter()
    End Function

    Public Shared Function CreateParameter(ByVal name As String, ByVal type As DbType, ByVal value As Object) As DbParameter
        Dim factory As DbProviderFactory = GetFactory()
        Dim param As DbParameter = factory.CreateParameter()
        param.ParameterName = name
        param.DbType = type
        param.Value = value
        Return param
    End Function

    Public Function CheckNull(ByVal obj As Object) As Object
        If TypeOf obj Is DBNull Then Return ""
        Return obj
    End Function

    Public Function GetInt(ByVal obj As Object) As Integer
        If TypeOf obj Is DBNull Then Return 0
        Return Convert.ToInt32(obj.ToString())
    End Function

    Public Function GetDecimal(ByVal obj As Object) As Decimal
        If TypeOf obj Is DBNull Then Return 0
        Return Convert.ToDecimal(obj.ToString())
    End Function

    Public Function GetDouble(ByVal obj As Object) As Double
        If TypeOf obj Is DBNull Then Return 0
        Return Convert.ToDouble(obj.ToString())
    End Function

    Public Function GetBit(ByVal obj As Object) As Integer
        If TypeOf obj Is DBNull Then Return 0

        If obj.ToString() = "True" Then
            Return 1
        Else
            Return 0
        End If
    End Function

    Public Function GetString(ByVal obj As Object) As String
        If TypeOf obj Is DBNull Then Return ""
        Return Convert.ToString(obj.ToString())
    End Function

    Public Function GetBoolean(ByVal obj As Object) As Boolean
        If TypeOf obj Is DBNull Then Return False
        If obj.ToString() = "True" Then Return True
        If obj.ToString() = "1" Then Return True

        If obj.ToString() = "Yes" Then
            Return True
        Else
            Return False
        End If
    End Function

    Public Shared Function ConverToDateFormat(ByVal src As String) As DateTime
        Dim enUSDateFormat As IFormatProvider = New CultureInfo("en-GB").DateTimeFormat
        Return DateTime.Parse(src, enUSDateFormat)
    End Function

    Public Function GetDateStr(ByVal obj As Object) As String
        If TypeOf obj Is DBNull Then Return ""
        Return (CDate(obj)).ToString("dd/MM/yyyy")
    End Function

    Public Function GetDateFromStr(ByVal str As String) As DateTime
        Return ConverToDateFormat(str)
    End Function

    Public Function SetDateTime(ByVal str As String) As String
        If str = "" Then str = "01/01/1900"
        Dim enIndiaDateFormat As IFormatProvider = New CultureInfo("en-GB").DateTimeFormat
        Return (DateTime.Parse(str, enIndiaDateFormat)).ToString("MM/dd/yyyy")
    End Function

    Public Function GetDateTime(ByVal obj As Object) As DateTime
        Dim sDtm As DateTime
        If TypeOf obj Is DBNull Then Return Convert.ToDateTime("1/1/1900")
        Dim src As String = obj.ToString()

        If DateTime.TryParse(src, sDtm) Then
            Return sDtm
        Else
            Return Convert.ToDateTime("1/1/1900")
        End If
    End Function

    Public Function GetPage(ByVal spName As String, ByVal startIndex As Integer, ByVal pageSize As Integer, ByVal sortBy As String, ByVal filterBy As String, ByRef total As Integer) As DataSet
        Try
            Dim cmd As DbCommand = GetCommand()
            cmd.CommandText = spName
            cmd.CommandType = CommandType.StoredProcedure
            cmd.Parameters.Add(CreateParameter("@startIndex", DbType.Int32, startIndex))
            cmd.Parameters.Add(CreateParameter("@pageSize", DbType.Int32, pageSize))
            cmd.Parameters.Add(CreateParameter("@sortBy", DbType.String, sortBy))
            cmd.Parameters.Add(CreateParameter("@filterBy", DbType.String, filterBy))
            cmd.Parameters.Add(CreateParameter("@totalCount", DbType.Int32, 0))
                (CType(cmd.Parameters("@totalCount"), SqlParameter)).Direction = ParameterDirection.Output
                Dim results As DataSet = FillData(cmd)
            total = Convert.ToInt32((CType(cmd.Parameters("@totalCount"), SqlParameter)).Value)
            cmd.Connection.Close()
            Return results
        Catch bEx As cException
            Throw bEx
        Catch sqlEx As SqlEx
            Throw New cException(cException.DATABASE_ERROR)
        Catch e As Exception
            Throw New cException(cException.INTERNAL_ERROR)
        End Try
    End Function

    Public Overridable Function [Get](ByVal spName As String, ByVal input As DbParameter()) As DataSet
        Try
            Dim cmd As DbCommand = GetCommand()
            cmd.CommandText = spName

            If input IsNot Nothing Then
                Dim len As Integer = input.Length

                For i As Integer = 0 To len - 1
                    cmd.Parameters.Add(input(i))
                Next
            End If

            Dim results As DataSet = FillData(cmd)
            cmd.Connection.Close()
            Return results
        Catch bEx As cException
            Throw bEx
        Catch sqlEx As SqlException
            Throw New cException(cException.DATABASE_ERROR)
        Catch e As Exception
            Throw New cException(cException.INTERNAL_ERROR)
        End Try
    End Function

    Public Overridable Function GetList(ByVal param As Object) As Object
        Try
            Dim cmd As DbCommand = GetCommand()
            PreGetList(param, cmd)
            Dim results As DataSet = FillData(cmd)
            cmd.Connection.Close()
            Return PostGetList(results)
        Catch bEx As cException
            Throw bEx
        Catch sqlEx As SqlException
            Throw New cException(cException.DATABASE_ERROR)
        Catch e As Exception
            Throw New cException(cException.INTERNAL_ERROR)
        End Try
    End Function

    Public Overridable Function GetDataList(ByVal param As Object) As Object
        Try
            Dim cmd As DbCommand = GetCommand()
            PreGetDataList(param, cmd)
            Dim results As DataSet = FillData(cmd)
            cmd.Connection.Close()
            Return PostGetDataList(results)
        Catch bEx As cException
            Throw bEx
        Catch sqlEx As SqlException
            Throw New cException(cException.DATABASE_ERROR)
        Catch e As Exception
            Throw New cException(cException.INTERNAL_ERROR)
        End Try
    End Function

    Public Overridable Function [Get](ByVal param As Object) As Object
        Try
            Dim cmd As DbCommand = GetCommand()
            PreGet(param, cmd)
            Dim results As DataSet = FillData(cmd)
            cmd.Connection.Close()
            Return PostGet(results)
        Catch bEx As cException
            Throw bEx
        Catch sqlEx As SqlException
            Throw New cException(cException.DATABASE_ERROR)
        Catch e As Exception
            Throw New cException(cException.INTERNAL_ERROR)
        End Try
    End Function

    Public Overridable Function GetDetails(ByVal param As Object) As Object
        Try
            Dim cmd As DbCommand = GetCommand()
            PreGetDetails(param, cmd)
            Dim results As DataSet = FillData(cmd)
            cmd.Connection.Close()
            Return PostGetDetails(results)
        Catch bEx As cException
            Throw bEx
        Catch sqlEx As SqlException
            Throw New cException(cException.DATABASE_ERROR)
        Catch e As Exception
            Throw New cException(cException.INTERNAL_ERROR)
        End Try
    End Function

    Public Overridable Sub Add(ByVal data As Object)
        Try
            Dim cmd As DbCommand = GetCommand()
            PreInsert(data, cmd)
            AddReturnValue(cmd)
            cmd.ExecuteNonQuery()
            Dim retValue As Integer = GetReturnValue(cmd)

            If retValue > 0 Then
                Throw New cException(retValue)
            End If

            PostInsert(data, cmd)
        Catch bEx As cException
            Throw bEx
        Catch sqlEx As SqlException
            If sqlEx.Message.Contains("Validation Failed") Then Throw New cException(cException.ERROR_VALIDATION, sqlEx.Message)
            Throw New cException(cException.DATABASE_ERROR)
        Catch e As Exception
            Throw New cException(cException.INTERNAL_ERROR)
        End Try
    End Sub

    Public Overridable Sub PostAdd(ByVal data As Object)
        Try
            Dim cmd As DbCommand = GetCommand()
            PrePostAdd(data, cmd)
            If cmd.CommandText = "" Then Return
            AddReturnValue(cmd)
            cmd.ExecuteNonQuery()
            Dim retValue As Integer = GetReturnValue(cmd)

            If retValue > 0 Then
                Throw New cException(retValue)
            End If

            PostPostAdd(data, cmd)
        Catch bEx As cException
            Throw bEx
        Catch sqlEx As SqlException
            Throw New cException(cException.DATABASE_ERROR, sqlEx.Message)
        Catch e As Exception
            Throw New cException(cException.INTERNAL_ERROR)
        End Try
    End Sub

    Public Overridable Function Updatable(ByVal param As Object) As Object
        Try
            Dim cmd As DbCommand = GetCommand()
            PreUpdatable(param, cmd)
            Dim results As DataSet = FillData(cmd)
            cmd.Connection.Close()
            Return PostUpdatable(results)
        Catch bEx As cException
            Throw bEx
        Catch sqlEx As SqlException
            Throw New cException(cException.DATABASE_ERROR, sqlEx.Message)
        Catch e As Exception
            Throw New cException(cException.INTERNAL_ERROR, e.Message)
        End Try
    End Function

    Public Overridable Sub Update(ByVal data As Object)
        Try
            Dim cmd As DbCommand = GetCommand()
            PreUpdate(data, cmd)
            AddReturnValue(cmd)
            cmd.ExecuteNonQuery()
            Dim retValue As Integer = GetReturnValue(cmd)

            If retValue > 0 Then
                Throw New cException(retValue)
            End If

            PostUpdate(data, cmd)
        Catch bEx As cException
            Throw bEx
        Catch sqlEx As SqlException
            Throw New cException(cException.DATABASE_ERROR)
        Catch e As Exception
            Throw New cException(cException.INTERNAL_ERROR)
        End Try
    End Sub

    Public Overridable Sub Amend(ByVal data As Object)
        Try
            Dim cmd As DbCommand = GetCommand()
            PreAmend(data, cmd)
            AddReturnValue(cmd)
            cmd.ExecuteNonQuery()
            Dim retValue As Integer = GetReturnValue(cmd)

            If retValue > 0 Then
                Throw New cException(retValue)
            End If

            PostAmend(data, cmd)
        Catch bEx As cException
            Throw bEx
        Catch sqlEx As SqlException
            Throw New cException(cException.DATABASE_ERROR)
        Catch e As Exception
            Throw New cException(cException.INTERNAL_ERROR)
        End Try
    End Sub

    Public Overridable Sub ShortClose(ByVal data As Object)
        Try
            Dim cmd As DbCommand = GetCommand()
            PreShortClose(data, cmd)
            Dim results As DataSet = FillData(cmd)
            cmd.Connection.Close()
        Catch bEx As cException
            Throw bEx
        Catch sqlEx As SqlException
            Throw New cException(cException.DATABASE_ERROR)
        Catch e As Exception
            Throw New cException(cException.INTERNAL_ERROR)
        End Try
    End Sub

    Public Overridable Sub Cancel(ByVal data As Object)
        Try
            Dim cmd As DbCommand = GetCommand()
            PreCancel(data, cmd)
            Dim results As DataSet = FillData(cmd)
            cmd.Connection.Close()
        Catch bEx As cException
            Throw bEx
        Catch sqlEx As SqlException
            Throw New cException(cException.DATABASE_ERROR)
        Catch e As Exception
            Throw New cException(cException.INTERNAL_ERROR)
        End Try
    End Sub

    Public Overridable Sub Delete(ByVal data As Object)
        Try
            Dim cmd As DbCommand = GetCommand()
            PreDelete(data, cmd)
            AddReturnValue(cmd)
            cmd.ExecuteNonQuery()
            Dim retValue As Integer = GetReturnValue(cmd)

            If retValue > 0 Then
                Throw New cException(retValue)
            End If

            PostDelete(data, cmd)
        Catch bEx As cException
            Throw bEx
        Catch sqlEx As SqlException
            Throw New cException(cException.DATABASE_ERROR)
        Catch e As Exception
            Throw New cException(cException.INTERNAL_ERROR)
        End Try
    End Sub

    Public Overridable Sub Upload(ByVal data As Object)
        Try
            Dim cmd As DbCommand = GetCommand()
            PreUpload(data, cmd)
            Dim results As DataSet = FillData(cmd)
            PostUpload(data, cmd)
        Catch bEx As cException
            Throw bEx
        Catch sqlEx As SqlException
            Throw New cException(cException.DATABASE_ERROR)
        Catch e As Exception
            Throw New cException(cException.INTERNAL_ERROR)
        End Try
    End Sub

    Private Function FillData(ByVal cmd As DbCommand) As DataSet
        Dim adapter As DbDataAdapter = CreateDataAdapter()
        adapter.SelectCommand = cmd
        Dim results As DataSet = New DataSet()
        adapter.Fill(results)
        Return results
    End Function

    Protected Function GetConnection() As DbConnection
        Dim connection As DbConnection = Nothing

        If _parent Is Nothing Then
            connection = CreateConnection()
        Else
            connection = _parent.Connection
        End If

        If connection Is Nothing Then Throw New cException(CREATE_CONNECTION_ERROR)
        Return connection
    End Function

    Protected Function GetCommand() As DbCommand
        Try
            Dim connection As DbConnection = GetConnection()
            Dim cmd As DbCommand = connection.CreateCommand()
            cmd.CommandType = CommandType.StoredProcedure
            cmd.Transaction = _parent.Transaction
            Return cmd
        Catch sqlEx As SqlException
            Throw New cException(CREATE_DBCOMMAND_ERROR)
        End Try
    End Function

    Protected Sub AddReturnValue(ByVal cmd As DbCommand)
        Dim returnValue As SqlParameter = New SqlParameter("@Return_Value", DbType.Int32)
        returnValue.Direction = ParameterDirection.ReturnValue
        cmd.Parameters.Add(returnValue)
    End Sub

    Protected Sub AddReturnValue(ByVal cmd As DbCommand, ByVal ParamName As String, ByVal type As DbType)
        Dim returnValue As SqlParameter = New SqlParameter(ParamName, type)
        returnValue.Direction = ParameterDirection.ReturnValue
        cmd.Parameters.Add(returnValue)
    End Sub

    Protected Function GetReturnValue(ByVal cmd As DbCommand) As Integer
        Return Int32.Parse(cmd.Parameters("@Return_Value").Value.ToString())
    End Function

    Protected Overridable Sub PreGet(ByVal param As Object, ByVal cmd As IDbCommand)
    End Sub

    Protected Overridable Function PostGet(ByVal results As DataSet) As Object
        Return results
    End Function

    Protected Overridable Sub PreGetDetails(ByVal param As Object, ByVal cmd As IDbCommand)
    End Sub

    Protected Overridable Function PostGetDetails(ByVal results As DataSet) As Object
        Return results
    End Function

    Protected Overridable Sub PreGetList(ByVal param As Object, ByVal cmd As IDbCommand)
    End Sub

    Protected Overridable Function PostGetList(ByVal results As DataSet) As Object
        Return results
    End Function

    Protected Overridable Sub PreGetDataList(ByVal param As Object, ByVal cmd As IDbCommand)
    End Sub

    Protected Overridable Function PostGetDataList(ByVal results As DataSet) As Object
        Return results
    End Function

    Protected Overridable Sub PreInsert(ByVal data As Object, ByVal cmd As IDbCommand)
    End Sub

    Protected Overridable Sub PostInsert(ByVal data As Object, ByVal cmd As IDbCommand)
    End Sub

    Protected Overridable Sub PrePostAdd(ByVal data As Object, ByVal cmd As IDbCommand)
    End Sub

    Protected Overridable Sub PostPostAdd(ByVal data As Object, ByVal cmd As IDbCommand)
    End Sub

    Protected Overridable Sub PreDelete(ByVal data As Object, ByVal cmd As IDbCommand)
    End Sub

    Protected Overridable Sub PostDelete(ByVal data As Object, ByVal cmd As IDbCommand)
    End Sub

    Protected Overridable Sub PreUpdate(ByVal data As Object, ByVal cmd As IDbCommand)
    End Sub

    Protected Overridable Sub PreAmend(ByVal data As Object, ByVal cmd As IDbCommand)
    End Sub

    Protected Overridable Sub PostAmend(ByVal data As Object, ByVal cmd As IDbCommand)
    End Sub

    Protected Overridable Sub PostUpdate(ByVal data As Object, ByVal cmd As IDbCommand)
    End Sub

    Protected Overridable Sub PopulateCommand(ByVal input As String(), ByVal cmd As IDbCommand)
    End Sub

    Protected Overridable Sub PopulateCommand(ByVal cmd As IDbCommand)
    End Sub

    Protected Overridable Sub PopulateCommandForPage(ByVal cmd As IDbCommand)
    End Sub

    Protected Overridable Sub PreUpdatable(ByVal data As Object, ByVal cmd As IDbCommand)
    End Sub

    Protected Overridable Function PostUpdatable(ByVal results As DataSet) As Integer
        If results.Tables Is Nothing OrElse results.Tables.Count <= 0 OrElse results.Tables(0).Rows.Count <= 0 Then Return 0
        Dim row As DataRow = results.Tables(0).Rows(0)
        Return GetInt(row("Updatable"))
    End Function

    Protected Overridable Sub PreShortClose(ByVal data As Object, ByVal cmd As IDbCommand)
    End Sub

    Protected Overridable Sub PreCancel(ByVal data As Object, ByVal cmd As IDbCommand)
    End Sub

    Protected Overridable Sub PreUpload(ByVal data As Object, ByVal cmd As IDbCommand)
    End Sub

    Protected Overridable Sub PostUpload(ByVal data As Object, ByVal cmd As IDbCommand)
    End Sub
End Class