Public Class BState
    Public Function GetListPage(StateName As String, Code As String, PageNum As Integer, PageSize As Integer) As DSClass
        Dim dState = New DState()
        Return dState.GetListPage(StateName, Code, PageNum, PageSize)
    End Function
    Public Sub Add(state As CState)
        Dim dState = New DState()
        dState.Add(state)
    End Sub
    Public Sub Update(state As CState)
        Dim dState = New DState()
        dState.Update(state)
    End Sub
    Public Function GetRow(ID As Integer) As CState
        Dim dState = New DState()
        Return dState.GetRow(ID)
    End Function
    Public Function GetList(IDs As Integer) As List(Of CState)
        Dim dState = New DState()
        Return dState.GetList(IDs)
    End Function
End Class
