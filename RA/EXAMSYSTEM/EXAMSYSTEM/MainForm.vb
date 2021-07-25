Public Class MainForm
    Private Sub Menu1_ItemClicked(sender As Object, e As ToolStripItemClickedEventArgs) Handles MainMenu.ItemClicked
        If e.ClickedItem.Name = "Logout" Then
            MessageBox.Show("Thank you for using Exam System! Good Bye!! You are logged out!!!")
            Me.Close()
        End If
    End Sub

    Private Sub Master_ItemClicked(sender As Object, e As ToolStripItemClickedEventArgs) Handles Master.DropDownItemClicked
        Dim menuText = e.ClickedItem.ToString()

        Select Case (menuText)
            Case "State"
                Dim stateForm As New State()
                stateForm.MdiParent = Me
                stateForm.Show()
            Case "City"
                Dim cityForm As New City()
                cityForm.MdiParent = Me
                cityForm.Show()
            Case "University"
                Dim univForm As New University()
                univForm.MdiParent = Me
                univForm.Show()

            Case "Subject"
                Dim subjectForm As New Subject()
                subjectForm.MdiParent = Me
                subjectForm.Show()

            Case "Semister"
                Dim semisterForm As New Semister()
                semisterForm.MdiParent = Me
                semisterForm.Show()

            Case "Branches"
                Dim branchesForm As New Branch()
                branchesForm.MdiParent = Me
                branchesForm.Show()
            Case Else

        End Select
    End Sub

    Private Sub Tranaction_ItemClicked(sender As Object, e As ToolStripItemClickedEventArgs) Handles Transaction.DropDownItemClicked
        Dim menuText = e.ClickedItem.ToString()
        Debug.Write(menuText)
        Select Case (menuText)
            Case "State"
                Dim stateForm As New State()
                stateForm.MdiParent = Me
                stateForm.Show()
            Case "City"
                Dim cityForm As New City()
                cityForm.MdiParent = Me
                cityForm.Show()
            Case Else

        End Select
    End Sub


    Private Sub MainForm_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        Dim loginForm As New LoginForm
        Dim result = loginForm.ShowDialog(Me)
        Debug.Write(result)
        If result <> DialogResult.OK Then
            loginForm.Dispose()
            Me.Close()
        Else
            REM user authentication is successfull and you can show them the screen
            Debug.Write("User logged in!")
        End If
    End Sub
End Class
