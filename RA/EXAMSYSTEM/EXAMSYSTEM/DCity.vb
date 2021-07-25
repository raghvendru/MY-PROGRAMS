Public Class DCity
    Public Sub Add(City As CCity)
        Dim param() As String = {"pName", "pCode", "pStateID"}
        Dim val() As String = {City.Name, City.Code, City.StateID}

        DbAccess.Instance().ExcuteSPNQ("sCityAdd", param, val)
    End Sub

    Public Sub Update(City As CCity)
        Dim dbAccess As DbAccess = DbAccess.Instance()
        Dim param() As String = {"pName", "pCode", "pStateID", "pCityID"}
        Dim val() As String = {City.Name, City.Code, City.StateID.ToString, City.CityID.ToString}
        dbAccess.ExcuteSPNQ("sCityUpdate", param, val)
    End Sub
    Private Sub PopulateClass(City As CCity, row As DataRow)
        City.Code = row.Item("Code")
        City.Name = row.Item("Name")
        City.StateID = row.Item("StateID")
        City.CityID = row.Item("CityID")

    End Sub
    Public Function GetRow(ID As Integer) As CCity
        Dim dbAccess As DbAccess = DbAccess.Instance()
        Dim param() As String = {"pCityID"}
        Dim val() As String = {ID}
        Dim dt As DataTable = dbAccess.ExcuteSPDT("sCityGet", param, val)
        Dim City As CCity = New CCity()
        If (dt.Rows.Count = 1) Then
            PopulateClass(City, dt.Rows(0))
        End If
        Return City
    End Function
    Public Function GetList(IDs As Integer) As List(Of CCity)
        Dim dbAccess As DbAccess = DbAccess.Instance()
        Dim param() As String = {"pCityIDs"}
        Dim val() As String = {IDs.ToString}
        Dim dt = dbAccess.ExcuteSPDT("sCityGetList", param, val)
        Dim list As List(Of CCity) = New List(Of CCity)
        For Each row In dt.Rows
            Dim City = New CCity()
            PopulateClass(City, row)
            list.Add(City)
        Next
        Return list
    End Function
    Public Function GetListPage(CityName As String, Code As String, StateID As Integer, PageNum As Integer, PageSize As Integer) As DSClass
        Dim dbAccess As DbAccess = DbAccess.Instance()
        Dim param() As String = {"pCityName", "pCode", "pStateID", "pPageNum", "pPageSize"}
        Dim val() As String = {CityName, Code, StateID.ToString, PageNum.ToString, PageSize.ToString}
        Dim ds = dbAccess.ExcuteSPDS("sCityGetListPage", param, val)
        Dim retObj As DSClass = New DSClass()
        retObj.numRows = ds.Tables(0).Rows(0).Item(0)
        Dim dt = ds.Tables(1)
        ' convert the DATATABLE to list of CCityobjects
        Dim list As List(Of CCity) = New List(Of CCity)
        For Each row In dt.Rows
            Dim City = New CCity()
            PopulateClass(City, row)
            list.Add(City)
        Next
        retObj.obj = CType(list, Object)
        Return retObj
    End Function

End Class
