Imports MySql.Data.MySqlClient
Public Class Semister
    Dim mode As Integer REM 0 is new 1 is update
    Dim isModified As Boolean
    Dim list As List(Of CSemister)
    'Dim ds As DataSet
    'Dim dt As DataTable
    Dim bSemister As BSemister = New BSemister()
    Dim Semister As CSemister
    Dim rows As DataGridViewRowCollection
    Dim numPage = 1
    Dim pageSize = 20
    Dim totalRec = 0
    Dim showRec = 0
    Private Sub setFieldsSelectedIndex()
        If (ListBox1.SelectedIndex > -1) Then
            Semister = list(ListBox1.SelectedIndex)
            With Semister
                TextBSName.Text = .SemisterName
                TextSCode.Text = .SemisterCode
            End With
            mode = 1
        End If
    End Sub
    Private Sub LoadObjectList()
        'Dim pName() As String = {"pStateName", "pCode", "pPageNum", "pPageSize"}
        'Dim pValue() As String = {"", "", "1", "20"}
        'ds = DbAccess.Instance.ExcuteSPDS("sStateGetListPage", pName, pValue)
        'If (ds.Tables.Count = 2) Then
        '    lblRows.Text = "Showing " + ds.Tables(0).Rows(0).Item(0).ToString + " records"
        '    dt = ds.Tables(1)
        '    ListBox1.DataSource = dt
        '    ListBox1.DisplayMember = "Name"
        'End If
        Dim retObj As DSClass
        retObj = bSemister.GetListPage("", "", 1, numPage)
        totalRec = retObj.numRows
        If (totalRec > pageSize) Then
            showRec = pageSize
        Else
            showRec = totalRec
        End If
        IblRows.Text = "Showing " + showRec.ToString + " of " + totalRec.ToString + " records"
        list = CType(retObj.obj, List(Of CSemister))
        ListBox1.DisplayMember = "Name"
        ListBox1.DataSource = list
    End Sub
    Private Sub State_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        LoadObjectList()
        Me.WindowState = FormWindowState.Maximized
        isModified = False
    End Sub

    Private Sub GetNextPage()
        Dim retObj As DSClass
        If (Math.Round(totalRec / pageSize) > numPage) Then
            numPage += 1
            retObj = bSemister.GetListPage("", "", 1, numPage)
            Dim lst = CType(retObj.obj, List(Of CSemister))
            For Each item In lst
                list.Add(item)
            Next

            ListRefresh()
        End If

    End Sub
    Private Sub Save_Click(sender As Object, e As EventArgs) Handles BntSave.Click
        REM this can new record or old record ?
        If (mode = 0) Then
            InsertRec(TextBSName.Text, TextSCode.Text)
            MsgBox("Successfully added new record!")
        Else
            UpdateRec()
            MsgBox("Successfully updated record!")
        End If
        ListRefresh()
    End Sub

    Private Sub InsertRec(Name As String, Code As String)
        'Semister = New CSemister(Name, Code)
        'bSemister.Add(Semister)
        ''Dim pName() As String = {"pName", "pCode"}
        ''Dim pValue() As String = {TextBoxName.Text, TextBoxCode.Text}
        ''DbAccess.Instance.ExcuteSPNQ("sStateAdd", pName, pValue)
        'list.Add(Semister)
        'ListRefresh()
    End Sub
    Private Sub ListRefresh()
        Dim bs As BindingSource = New BindingSource()
        bs.DataSource = list
        ListBox1.DataSource = bs
        ListBox1.DisplayMember = "Name"
    End Sub

    Private Sub UpdateRec()
        'Dim pName() As String = {"pName", "pCode", "pStateID"}
        'Dim pValue() As String = {TextBoxName.Text, TextBoxCode.Text, TextBoxID.Text}
        'DbAccess.Instance.ExcuteSPNQ("sStateUpdate", pName, pValue)
        Dim index = ListBox1.SelectedIndex
        Semister.SemisterName = TextBSName.Text
        Semister.SemisterCode = TextSCode.Text
        bSemister.Update(Semister)
        list(index) = Semister
        ListRefresh()
        ListBox1.SelectedIndex = index
    End Sub

    Private Sub ResetFields()
        TextSCode.Text = ""
        TextBSName.Text = ""
        TextSID.Text = ""
    End Sub

    Private Sub Button3_Click(sender As Object, e As EventArgs) Handles BntCancel.Click
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

    Private Sub btnClose_Click(sender As Object, e As EventArgs) Handles BntClose.Click
        If isModified = True Then
            Dim res = MsgBox("Unsaved data is pending. Do you want to close?", MsgBoxStyle.OkCancel)
            If (res = MsgBoxResult.Ok) Then
                Me.Close()
            End If
        Else
            Me.Close()
        End If
    End Sub

    Private Sub TextBoxName_TextChanged(sender As Object, e As EventArgs) Handles TextBSName.TextChanged
        isModified = True
    End Sub

    Private Sub TextBoxCode_TextChanged(sender As Object, e As EventArgs) Handles TextSCode.TextChanged
        isModified = True
    End Sub




End Class
