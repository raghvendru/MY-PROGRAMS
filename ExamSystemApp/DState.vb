Public Class DState
    Public Sub Add(State As CState)
        Dim param() As String = {"pName", "pCode"}
        Dim val() As String = {State.Name, State.Code}
        DbAccess.Instance().ExcuteSPNQ("sStateAdd", param, val)
    End Sub

    Public Sub Update(State As CState)
        Dim dbAccess As DbAccess = DbAccess.Instance()
        Dim param() As String = {"pName", "pCode", "pStateID"}
        Dim val() As String = {State.Name, State.Code, State.ID.ToString}
        dbAccess.ExcuteSPNQ("sStateUpdate", param, val)
    End Sub
    Private Sub PopulateClass(state As CState, row As DataRow)
        state.Code = row.Item("Code")
        state.Name = row.Item("Name")
        state.ID = row.Item("StateID")
    End Sub
    Public Function GetRow(ID As Integer) As CState
        Dim dbAccess As DbAccess = DbAccess.Instance()
        Dim param() As String = {"pStateID"}
        Dim val() As String = {ID}
        Dim dt As DataTable = dbAccess.ExcuteSPDT("sStateGet", param, val)
        Dim state As CState = New CState()
        If (dt.Rows.Count = 1) Then
            PopulateClass(state, dt.Rows(0))
        End If
    End Function
    Public Function GetList(IDs As Integer) As List(Of CState)
        Dim dbAccess As DbAccess = DbAccess.Instance()
        Dim param() As String = {"pStateIDs"}
        Dim val() As String = {IDs.ToString}
        Dim dt = dbAccess.ExcuteSPDT("sStateGetList", param, val)
        Dim list As List(Of CState) = New List(Of CState)
        For Each row In dt.Rows
            Dim state = New CState()
            PopulateClass(state, row)
            list.Add(state)
        Next
        Return list
    End Function
    Public Function GetListPage(StateName As String, Code As String, PageNum As Integer, PageSize As Integer) As DSClass
        Dim dbAccess As DbAccess = DbAccess.Instance()
        Dim param() As String = {"pStateName", "pCode", "pPageNum", "pPageSize"}
        Dim val() As String = {StateName, Code, PageNum.ToString, PageSize.ToString}
        Dim ds = dbAccess.ExcuteSPDS("sStateGetListPage", param, val)
        Dim retObj As DSClass = New DSClass()
        retObj.numRows = ds.Tables(0).Rows(0).Item(0)
        Dim dt = ds.Tables(1)
        ' convert the DATATABLE to list of CState objects
        Dim list As List(Of CState) = New List(Of CState)
        For Each row In dt.Rows
            Dim state = New CState()
            PopulateClass(state, row)
            list.Add(state)
        Next
        retObj.obj = CType(list, Object)
        Return retObj
    End Function
End Class
