Public Class BSemister
    Public Function GetListPage(SemisterName As String, SemisterCode As String, PageNum As Integer, PageSize As Integer) As DSClass
        Dim dSemister = New DSemister()
        Return GetListPage(SemisterName, SemisterCode, PageNum)
    End Function

    Public Sub Add(Semister As CSemister)
        Dim dSemister = New DSemister()
        dSemister.Add(Semister)
    End Sub
    Public Sub Update(Semister As CSemister)
        Dim dSemister = New DSemister()
        dSemister.Update(Semister)
    End Sub
    Public Function GetRow(ID As Integer) As CSemister
        Dim dSemister = New DSemister()
        Return dSemister.GetRow(ID)
    End Function
    Public Function GetList(IDs As Integer) As List(Of CSemister)
        Dim dSemister = New DSemister()
        Return dSemister.GetList(IDs)
    End Function
End Class
