<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class MainForm
    Inherits System.Windows.Forms.Form

    'Form overrides dispose to clean up the component list.
    <System.Diagnostics.DebuggerNonUserCode()> _
    Protected Overrides Sub Dispose(ByVal disposing As Boolean)
        Try
            If disposing AndAlso components IsNot Nothing Then
                components.Dispose()
            End If
        Finally
            MyBase.Dispose(disposing)
        End Try
    End Sub

    'Required by the Windows Form Designer
    Private components As System.ComponentModel.IContainer

    'NOTE: The following procedure is required by the Windows Form Designer
    'It can be modified using the Windows Form Designer.  
    'Do not modify it using the code editor.
    <System.Diagnostics.DebuggerStepThrough()> _
    Private Sub InitializeComponent()
        Me.MainMenu = New System.Windows.Forms.MenuStrip()
        Me.Master = New System.Windows.Forms.ToolStripMenuItem()
        Me.State = New System.Windows.Forms.ToolStripMenuItem()
        Me.City = New System.Windows.Forms.ToolStripMenuItem()
        Me.University = New System.Windows.Forms.ToolStripMenuItem()
        Me.College = New System.Windows.Forms.ToolStripMenuItem()
        Me.Branches = New System.Windows.Forms.ToolStripMenuItem()
        Me.AcademicSemesters = New System.Windows.Forms.ToolStripMenuItem()
        Me.Subjects = New System.Windows.Forms.ToolStripMenuItem()
        Me.BranchSemester = New System.Windows.Forms.ToolStripMenuItem()
        Me.BranchSemesterSubject = New System.Windows.Forms.ToolStripMenuItem()
        Me.CollegeBranches = New System.Windows.Forms.ToolStripMenuItem()
        Me.Transaction = New System.Windows.Forms.ToolStripMenuItem()
        Me.Reports = New System.Windows.Forms.ToolStripMenuItem()
        Me.Logout = New System.Windows.Forms.ToolStripMenuItem()
        Me.newMenuItem = New System.Windows.Forms.ToolStripMenuItem()
        Me.newMenuItem2 = New System.Windows.Forms.ToolStripMenuItem()
        Me.ExamSchedule = New System.Windows.Forms.ToolStripMenuItem()
        Me.ExamTT = New System.Windows.Forms.ToolStripMenuItem()
        Me.ExamAttendance = New System.Windows.Forms.ToolStripMenuItem()
        Me.ExamResult = New System.Windows.Forms.ToolStripMenuItem()
        Me.MainMenu.SuspendLayout()
        Me.SuspendLayout()
        '
        'MainMenu
        '
        Me.MainMenu.Items.AddRange(New System.Windows.Forms.ToolStripItem() {Me.Master, Me.Transaction, Me.Reports, Me.Logout, Me.newMenuItem, Me.newMenuItem2})
        Me.MainMenu.Location = New System.Drawing.Point(0, 0)
        Me.MainMenu.Name = "MainMenu"
        Me.MainMenu.Size = New System.Drawing.Size(1098, 24)
        Me.MainMenu.TabIndex = 0
        Me.MainMenu.Text = "Main Menu"
        '
        'Master
        '
        Me.Master.DropDownItems.AddRange(New System.Windows.Forms.ToolStripItem() {Me.State, Me.City, Me.University, Me.College, Me.Branches, Me.AcademicSemesters, Me.Subjects, Me.BranchSemester, Me.BranchSemesterSubject, Me.CollegeBranches})
        Me.Master.Name = "Master"
        Me.Master.Size = New System.Drawing.Size(55, 20)
        Me.Master.Text = "Master"
        '
        'State
        '
        Me.State.Name = "State"
        Me.State.Size = New System.Drawing.Size(179, 22)
        Me.State.Text = "State"
        '
        'City
        '
        Me.City.Name = "City"
        Me.City.Size = New System.Drawing.Size(179, 22)
        Me.City.Text = "City"
        '
        'University
        '
        Me.University.Name = "University"
        Me.University.Size = New System.Drawing.Size(179, 22)
        Me.University.Text = "University"
        '
        'College
        '
        Me.College.Name = "College"
        Me.College.Size = New System.Drawing.Size(179, 22)
        Me.College.Text = "College"
        '
        'Branches
        '
        Me.Branches.Name = "Branches"
        Me.Branches.Size = New System.Drawing.Size(179, 22)
        Me.Branches.Text = "Branches"
        '
        'AcademicSemesters
        '
        Me.AcademicSemesters.Name = "AcademicSemesters"
        Me.AcademicSemesters.Size = New System.Drawing.Size(179, 22)
        Me.AcademicSemesters.Text = "AS"
        '
        'Subjects
        '
        Me.Subjects.Name = "Subjects"
        Me.Subjects.Size = New System.Drawing.Size(179, 22)
        Me.Subjects.Text = "Subjects"
        '
        'BranchSemester
        '
        Me.BranchSemester.Name = "BranchSemester"
        Me.BranchSemester.Size = New System.Drawing.Size(179, 22)
        Me.BranchSemester.Text = "Brach Semester"
        '
        'BranchSemesterSubject
        '
        Me.BranchSemesterSubject.Name = "BranchSemesterSubject"
        Me.BranchSemesterSubject.Size = New System.Drawing.Size(179, 22)
        Me.BranchSemesterSubject.Text = "Branch Sem Subject"
        '
        'CollegeBranches
        '
        Me.CollegeBranches.Name = "CollegeBranches"
        Me.CollegeBranches.Size = New System.Drawing.Size(179, 22)
        Me.CollegeBranches.Text = "College Branch"
        '
        'Transaction
        '
        Me.Transaction.DropDownItems.AddRange(New System.Windows.Forms.ToolStripItem() {Me.ExamSchedule, Me.ExamTT, Me.ExamAttendance, Me.ExamResult})
        Me.Transaction.Name = "Transaction"
        Me.Transaction.Size = New System.Drawing.Size(79, 20)
        Me.Transaction.Text = "Transaction"
        '
        'Reports
        '
        Me.Reports.Name = "Reports"
        Me.Reports.Size = New System.Drawing.Size(59, 20)
        Me.Reports.Text = "Reports"
        '
        'Logout
        '
        Me.Logout.Name = "Logout"
        Me.Logout.Size = New System.Drawing.Size(57, 20)
        Me.Logout.Text = "Logout"
        '
        'newMenuItem
        '
        Me.newMenuItem.Name = "newMenuItem"
        Me.newMenuItem.Size = New System.Drawing.Size(96, 20)
        Me.newMenuItem.Text = "newMenuItem"
        '
        'newMenuItem2
        '
        Me.newMenuItem2.Name = "newMenuItem2"
        Me.newMenuItem2.Size = New System.Drawing.Size(102, 20)
        Me.newMenuItem2.Text = "newMenuItem2"
        '
        'ExamSchedule
        '
        Me.ExamSchedule.Name = "ExamSchedule"
        Me.ExamSchedule.Size = New System.Drawing.Size(167, 22)
        Me.ExamSchedule.Text = "Exam Schedule"
        '
        'ExamTT
        '
        Me.ExamTT.Name = "ExamTT"
        Me.ExamTT.Size = New System.Drawing.Size(167, 22)
        Me.ExamTT.Text = "Exam TT"
        '
        'ExamAttendance
        '
        Me.ExamAttendance.Name = "ExamAttendance"
        Me.ExamAttendance.Size = New System.Drawing.Size(167, 22)
        Me.ExamAttendance.Text = "Exam Attendance"
        '
        'ExamResult
        '
        Me.ExamResult.Name = "ExamResult"
        Me.ExamResult.Size = New System.Drawing.Size(167, 22)
        Me.ExamResult.Text = "Exam Marks"
        '
        'MainForm
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(7.0!, 15.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(1098, 450)
        Me.Controls.Add(Me.MainMenu)
        Me.IsMdiContainer = True
        Me.MainMenuStrip = Me.MainMenu
        Me.Name = "MainForm"
        Me.Text = "Exam Systems Application"
        Me.WindowState = System.Windows.Forms.FormWindowState.Maximized
        Me.MainMenu.ResumeLayout(False)
        Me.MainMenu.PerformLayout()
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub

    Friend WithEvents MainMenu As MenuStrip
    Friend WithEvents Master As ToolStripMenuItem
    Friend WithEvents Transaction As ToolStripMenuItem
    Friend WithEvents Reports As ToolStripMenuItem
    Friend WithEvents Logout As ToolStripMenuItem
    Friend WithEvents State As ToolStripMenuItem
    Friend WithEvents City As ToolStripMenuItem
    Friend WithEvents University As ToolStripMenuItem
    Friend WithEvents College As ToolStripMenuItem
    Friend WithEvents Branches As ToolStripMenuItem
    Friend WithEvents AcademicSemesters As ToolStripMenuItem
    Friend WithEvents Subjects As ToolStripMenuItem
    Friend WithEvents BranchSemester As ToolStripMenuItem
    Friend WithEvents BranchSemesterSubject As ToolStripMenuItem
    Friend WithEvents CollegeBranches As ToolStripMenuItem
    Friend WithEvents ExamSchedule As ToolStripMenuItem
    Friend WithEvents ExamTT As ToolStripMenuItem
    Friend WithEvents ExamAttendance As ToolStripMenuItem
    Friend WithEvents ExamResult As ToolStripMenuItem
    Friend WithEvents newMenuItem As ToolStripMenuItem
    Friend WithEvents newMenuItem2 As ToolStripMenuItem
End Class
