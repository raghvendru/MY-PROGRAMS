Imports MySql.Data.MySqlClient
Public Class State
    Dim mode As Integer REM 0 is new 1 is update
    Dim isModified As Boolean
    Dim ds As DataSet
    Dim dt As DataTable
    Private Sub setFieldsSelectedIndex()
        If (ListBox1.SelectedIndex > -1) Then
            With dt.Rows(ListBox1.SelectedIndex)
                TextBoxID.Text = .Item("StateID")
                TextBoxName.Text = .Item("Name")
                TextBoxCode.Text = .Item("Code")
            End With
            mode = 1
        End If
    End Sub
    Private Sub LoadObjectList()
        Dim pName() As String = {"pStateName", "pCode", "pPageNum", "pPageSize"}
        Dim pValue() As String = {"", "", "1", "20"}
        ds = DbAccess.Instance.ExcuteSPDS("sStateGetListPage", pName, pValue)
        If (ds.Tables.Count = 2) Then
            lblRows.Text = "Showing " + ds.Tables(0).Rows(0).Item(0).ToString + " records"
            dt = ds.Tables(1)
            ListBox1.DataSource = dt
            ListBox1.DisplayMember = "Name"
        End If
    End Sub
    Private Sub State_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        LoadObjectList()
        Me.WindowState = FormWindowState.Maximized
        isModified = False
    End Sub

    Private Sub Save_Click(sender As Object, e As EventArgs) Handles btnSave.Click
        REM this can new record or old record ?
        If (mode = 0) Then
            Call InsertRec()
        Else
            Call UpdateRec()

        End If
    End Sub

    Private Sub InsertRec()
        Dim pName() As String = {"pName", "pCode"}
        Dim pValue() As String = {TextBoxName.Text, TextBoxCode.Text}
        DbAccess.Instance.ExcuteSPNQ("sStateAdd", pName, pValue)
    End Sub

    Private Sub UpdateRec()
        Dim pName() As String = {"pName", "pCode", "pStateID"}
        Dim pValue() As String = {TextBoxName.Text, TextBoxCode.Text, TextBoxID.Text}
        DbAccess.Instance.ExcuteSPNQ("sStateUpdate", pName, pValue)
    End Sub

    Private Sub ResetFields()
        TextBoxCode.Text = ""
        TextBoxName.Text = ""
        TextBoxID.Text = ""
    End Sub

    Private Sub Button3_Click(sender As Object, e As EventArgs) Handles btnCancel.Click
        If isModified = True Then
            Dim res = MsgBox("Are ou sure you dont want to save the data?", MsgBoxStyle.OkCancel)
            If (res = MsgBoxResult.Ok) Then
                ResetFields()
            End If
        End If
    End Sub

    Private Sub ListBox1_SelectedIndexChanged(sender As Object, e As EventArgs) Handles ListBox1.SelectedIndexChanged
        Call setFieldsSelectedIndex()
    End Sub

    Private Sub BtnNew_Click(sender As Object, e As EventArgs) Handles btnNew.Click
        Call ResetFields()
        mode = 0 ' New
    End Sub

    Private Sub btnClose_Click(sender As Object, e As EventArgs) Handles btnClose.Click
        If isModified = True Then
            Dim res = MsgBox("Unsaved data is pending. Do you want to close?", MsgBoxStyle.OkCancel)
            If (res = MsgBoxResult.Ok) Then
                Me.Close()
            End If
        Else
            Me.Close()
        End If
    End Sub

    Private Sub TextBoxName_TextChanged(sender As Object, e As EventArgs) Handles TextBoxName.TextChanged
        isModified = True
    End Sub

    Private Sub TextBoxCode_TextChanged(sender As Object, e As EventArgs) Handles TextBoxCode.TextChanged
        isModified = True
    End Sub

    Private Sub btnImport_Click(sender As Object, e As EventArgs) Handles btnImport.Click
        Dim importForm = New Import
        importForm.ShowDialog(Me)
    End Sub
End Class