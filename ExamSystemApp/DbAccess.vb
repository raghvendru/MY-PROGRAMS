Imports MySql.Data.MySqlClient
Public Class DbAccess
    ' this is a singleton class 
    ' this means only one instance is created 
    ' if some one requires this this instance is returned
    ' the called cannot create a class 
    ' Dim abc ClassName = new ClassName()
    ' this is not possible here 
    ' Dim dbAccess = new DbAccess() --- not possible. 
    ' Dim dbAccess As DBAccess
    ' dbAccess = DBAccess.Instance()
    Private Shared _instance As DbAccess
    Dim con As MySqlConnection = New MySqlConnection("server=13.71.16.66;user id=itfyme;password=itfyme;database=examsystem;sslMode=none")
    Dim cmd As MySqlCommand = New MySqlCommand
    Dim da As MySqlDataAdapter
    Dim dt As DataTable
    Dim ds As DataSet
    ' Constructor is 'protected'
    Protected Sub New()
    End Sub

    Public Shared Function Instance() As DbAccess
        If _instance Is Nothing Then
            _instance = New DbAccess()
        End If
        Return _instance
    End Function

    Private Sub init()
        If con.State <> ConnectionState.Open Then
            con.Open()
        End If
    End Sub
    Public Function ExcuteSPDT(spName As String, pName() As String, pValue As String()) As DataTable
        cmd = New MySqlCommand()
        da = New MySqlDataAdapter
        dt = New DataTable
        init()
        With cmd
            .Connection = con
            .CommandText = spName
            .CommandType = CommandType.StoredProcedure
        End With
        Dim idx = 0
        For Each item In pName
            cmd.Parameters.AddWithValue(item, pValue(idx))
            idx += 1
        Next
        da.SelectCommand = cmd
        da.Fill(dt)
        da.Dispose()
        Return dt
    End Function
    Public Sub ExcuteSPNQ(spName As String, pName() As String, pValue As String())
        cmd = New MySqlCommand()
        init()
        With cmd
            .Connection = con
            .CommandText = spName
            .CommandType = CommandType.StoredProcedure
        End With
        Dim idx = 0
        For Each item In pName
            cmd.Parameters.AddWithValue(item, pValue(idx))
            idx += 1
        Next
        cmd.Prepare()
        cmd.ExecuteNonQuery()
    End Sub
    Public Function ExcuteSPDS(spName As String, pName() As String, pValue As String()) As DataSet
        cmd = New MySqlCommand()
        da = New MySqlDataAdapter
        ds = New DataSet
        init()
        With cmd
            .Connection = con
            .CommandText = spName
            .CommandType = CommandType.StoredProcedure
        End With
        Dim idx = 0
        For Each item In pName
            cmd.Parameters.AddWithValue(item, pValue(idx))
            idx += 1
        Next
        cmd.Prepare()
        da.SelectCommand = cmd
        da.Fill(ds)
        da.Dispose()
        Return ds
    End Function
End Class
