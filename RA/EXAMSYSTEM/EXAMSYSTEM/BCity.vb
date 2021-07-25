Public Class BCity
    Public Function GetListPage(CityName As String, Code As String, StateID As Integer, PageNum As Integer, PageSize As Integer) As DSClass
        Dim dCity = New DCity()
        Return dCity.GetListPage(CityName, Code, StateID, PageNum, PageSize)
    End Function
    Public Sub Add(City As CCity)
        Dim dCity = New DCity()
        dCity.Add(City)
    End Sub
    Public Sub Update(City As CCity)
        Dim dCity = New DCity()
        dCity.Update(City)
    End Sub
    Public Function GetRow(ID As Integer) As CCity
        Dim dCity = New DCity()
        Return dCity.GetRow(ID)
    End Function
    Public Function GetList(IDs As Integer) As List(Of CCity)
        Dim dCity = New DCity()
        Return dCity.GetList(IDs)
    End Function

End Class
