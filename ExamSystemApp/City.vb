Imports MySql.Data.MySqlClient
Public Class City
    Dim con As MySqlConnection = New MySqlConnection("server=13.71.16.66;user id=itfyme;password=itfyme;database=examsystem;sslMode=none")
    Dim cmd As MySqlCommand
    Dim da As MySqlDataAdapter
    Dim dt As DataTable
    Dim ds As DataSet
    Dim stateDT As DataTable
    Dim sql As String
    Dim mxrow As Integer
    Dim mode As Integer REM 0 is new 1 is update
    Dim isModified As Boolean

    Public Function GetData(ByVal sql As String)
        Dim maxrow As Integer = 0

        Try
            con.Open()
            cmd = New MySqlCommand
            da = New MySqlDataAdapter
            dt = New DataTable
            ds = New DataSet

            With cmd
                .Connection = con
                .CommandText = sql
                .CommandType = CommandType.StoredProcedure
            End With

            cmd.Parameters.AddWithValue("pStateID", 0)
            cmd.Parameters.AddWithValue("pCityName", "")
            cmd.Parameters.AddWithValue("pCode", "")
            cmd.Parameters.AddWithValue("pPageNum", 1)
            cmd.Parameters.AddWithValue("pPageSize", 40)
            REM we are executing two sql queries and results come in two result set
            REM use dataset to get the result
            da.SelectCommand = cmd
            REM da.Fill(dt)
            da.Fill(ds)

            'Now the data contain two tables and their row count may be zero 
            If ds.Tables(0).Rows.Count > 0 Then
                REM ds contains mulitple tables which you can loop to get the data
                Dim nRows = ds.Tables(0).Rows(0).Item(0)
                lblRows.Text = "Showing " + nRows.ToString + " records"
                dt = ds.Tables(1)
                maxrow = dt.Rows.Count
            End If
            maxrow = dt.Rows.Count

        Catch ex As Exception
            Debug.Write(ex.StackTrace)
            MessageBox.Show(ex.Message)
        Finally
            con.Close()

            'da.Dispose()
        End Try
        Return maxrow
    End Function

    Private Sub CallSelect()
        Dim nRows As Integer
        nRows = 0
        sql = "sCityGetListPage"
        mxrow = GetData(sql)
        'MessageBox.Show("rows returned " + mxrow.ToString)
        If mxrow > 0 Then
            Do While (nRows < mxrow)
                ListBox1.Items.Add(dt.Rows(nRows).Item("Name"))
                nRows += 1
            Loop
        End If
    End Sub


    Private Sub setFieldsSelectedIndex()
        If (ListBox1.SelectedIndex > -1) Then
            With dt.Rows(ListBox1.SelectedIndex)
                cmbState.SelectedValue = .Item("StateID")
                TextBoxID.Text = .Item("CityID")
                TextBoxName.Text = .Item("Name")
                TextBoxCode.Text = .Item("Code")
            End With
            mode = 1
        End If
    End Sub

    Private Sub StatePopulate()
        sql = "sStateGetList"
        Dim pName() As String = {"pStateIDs"}
        Dim pValue() As String = {""}
        stateDT = DbAccess.Instance.ExcuteSPDT(sql, pName, pValue)
        cmbState.DataSource = stateDT.DefaultView
        cmbState.DisplayMember = "Name"
        cmbState.ValueMember = "StateID"

        ' retrive the state and bind to combo box
        'con.Open()
        'cmd = New MySqlCommand
        'da = New MySqlDataAdapter
        'stateDT = New DataTable

        'With cmd
        '    .Connection = con
        '    .CommandText = "sStateGetList"
        '    .CommandType = CommandType.StoredProcedure
        'End With

        'cmd.Parameters.AddWithValue("pStateIDs", "")
        'da.SelectCommand = cmd
        'da.Fill(stateDT)
        'cmbState.DataSource = stateDT.DefaultView
        'cmbState.DisplayMember = "Name"
        'cmbState.ValueMember = "StateID"
        ''Do While (nRows < stateDT.Rows.Count)
        '    cmbState.Items.Add(stateDT.Rows(nRows).Item("Name"))
        '    nRows += 1
        'Loop
    End Sub
    Private Sub LoadObjectList()
        sql = "sCityGetListPage"
        Dim pName() As String = {"pStateID", "pCityName", "pCode", "pPageNum", "pPageSize"}
        Dim pValue() As String = {"0", "", "", "1", "20"}
        ds = DbAccess.Instance.ExcuteSPDS(sql, pName, pValue)
        If (ds.Tables.Count = 2) Then
            mxrow = ds.Tables(0).Rows(0).Item(0)
            lblRows.Text = "Showing " + mxrow.ToString + " records"
            dt = ds.Tables(1)
            ListBox1.DataSource = dt
            ListBox1.DisplayMember = "Name"
        End If
    End Sub
    Private Sub State_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        Call StatePopulate()
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
        Dim sql = "sCityAdd"
        Try
            con.Open()
            cmd = New MySqlCommand
            da = New MySqlDataAdapter
            dt = New DataTable
            With cmd
                .Connection = con
                .CommandText = sql
                .CommandType = CommandType.StoredProcedure
            End With

            cmd.Parameters.AddWithValue("pStateID", cmbState.SelectedValue)
            cmd.Parameters.AddWithValue("pName", TextBoxName.Text)
            cmd.Parameters.AddWithValue("pCode", TextBoxCode.Text)
            cmd.Prepare()
            cmd.ExecuteNonQuery()
            isModified = False
            MessageBox.Show("Inserted!")
        Catch ex As Exception
            Debug.Write(ex.StackTrace)
            MessageBox.Show(ex.Message)
        Finally
            con.Close()
        End Try
    End Sub

    Private Sub UpdateRec()
        Dim sql = "sCityUpdate"

        Try
            con.Open()
            cmd = New MySqlCommand
            da = New MySqlDataAdapter
            dt = New DataTable
            With cmd
                .Connection = con
                .CommandText = sql
                .CommandType = CommandType.StoredProcedure
            End With

            cmd.Parameters.AddWithValue("pStateID", cmbState.SelectedValue)
            cmd.Parameters.AddWithValue("pName", TextBoxName.Text)
            cmd.Parameters.AddWithValue("pCode", TextBoxCode.Text)
            cmd.Parameters.AddWithValue("pCityID", TextBoxID.Text)
            cmd.Prepare()
            cmd.ExecuteNonQuery()
            isModified = False
            MessageBox.Show("Updated!")
        Catch ex As Exception
            Debug.Write(ex.StackTrace)
            MessageBox.Show(ex.Message)
        Finally
            con.Close()
        End Try
    End Sub

    Private Sub ResetFields()
        cmbState.Text = ""
        TextBoxCode.Text = ""
        TextBoxName.Text = ""
        TextBoxID.Text = ""
    End Sub

    Private Sub Button3_Click(sender As Object, e As EventArgs) Handles btnCancel.Click
        Me.Close()
    End Sub

    Private Sub ListBox1_SelectedIndexChanged(sender As Object, e As EventArgs) Handles ListBox1.SelectedIndexChanged
        REM sender has selected item
        REM listbox1's selected index would be set
        REM MessageBox.Show(ListBox1.SelectedIndex.ToString)
        Call setFieldsSelectedIndex()
    End Sub

    Private Sub BtnNew_Click(sender As Object, e As EventArgs) Handles btnNew.Click
        Call ResetFields()
        mode = 0
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

End Class