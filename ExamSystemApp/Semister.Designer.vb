<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class Semister
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
        Me.btnNew = New System.Windows.Forms.Button()
        Me.ListBox1 = New System.Windows.Forms.ListBox()
        Me.TextBSName = New System.Windows.Forms.TextBox()
        Me.TextSCode = New System.Windows.Forms.TextBox()
        Me.TextSID = New System.Windows.Forms.TextBox()
        Me.LabSN = New System.Windows.Forms.Label()
        Me.LabSC = New System.Windows.Forms.Label()
        Me.LabSID = New System.Windows.Forms.Label()
        Me.BntCancel = New System.Windows.Forms.Button()
        Me.BntSave = New System.Windows.Forms.Button()
        Me.BntClose = New System.Windows.Forms.Button()
        Me.TextBox4 = New System.Windows.Forms.TextBox()
        Me.IblRows = New System.Windows.Forms.Label()
        Me.BntImport = New System.Windows.Forms.Button()
        Me.SuspendLayout()
        '
        'btnNew
        '
        Me.btnNew.Location = New System.Drawing.Point(402, 228)
        Me.btnNew.Name = "btnNew"
        Me.btnNew.Size = New System.Drawing.Size(56, 23)
        Me.btnNew.TabIndex = 17
        Me.btnNew.Text = "New"
        Me.btnNew.UseVisualStyleBackColor = True
        '
        'ListBox1
        '
        Me.ListBox1.FormattingEnabled = True
        Me.ListBox1.ItemHeight = 15
        Me.ListBox1.Location = New System.Drawing.Point(53, 141)
        Me.ListBox1.Name = "ListBox1"
        Me.ListBox1.Size = New System.Drawing.Size(120, 259)
        Me.ListBox1.TabIndex = 18
        '
        'TextBSName
        '
        Me.TextBSName.Location = New System.Drawing.Point(385, 70)
        Me.TextBSName.Name = "TextBSName"
        Me.TextBSName.Size = New System.Drawing.Size(167, 23)
        Me.TextBSName.TabIndex = 19
        '
        'TextSCode
        '
        Me.TextSCode.Location = New System.Drawing.Point(385, 119)
        Me.TextSCode.Name = "TextSCode"
        Me.TextSCode.Size = New System.Drawing.Size(167, 23)
        Me.TextSCode.TabIndex = 20
        '
        'TextSID
        '
        Me.TextSID.Location = New System.Drawing.Point(385, 163)
        Me.TextSID.Name = "TextSID"
        Me.TextSID.ReadOnly = True
        Me.TextSID.Size = New System.Drawing.Size(167, 23)
        Me.TextSID.TabIndex = 21
        '
        'LabSN
        '
        Me.LabSN.AutoSize = True
        Me.LabSN.Location = New System.Drawing.Point(275, 73)
        Me.LabSN.Name = "LabSN"
        Me.LabSN.Size = New System.Drawing.Size(87, 15)
        Me.LabSN.TabIndex = 22
        Me.LabSN.Text = "Semister Name"
        '
        'LabSC
        '
        Me.LabSC.AutoSize = True
        Me.LabSC.Location = New System.Drawing.Point(275, 119)
        Me.LabSC.Name = "LabSC"
        Me.LabSC.Size = New System.Drawing.Size(83, 15)
        Me.LabSC.TabIndex = 23
        Me.LabSC.Text = "Semister Code"
        '
        'LabSID
        '
        Me.LabSID.AutoSize = True
        Me.LabSID.Location = New System.Drawing.Point(275, 166)
        Me.LabSID.Name = "LabSID"
        Me.LabSID.Size = New System.Drawing.Size(66, 15)
        Me.LabSID.TabIndex = 24
        Me.LabSID.Text = "Semister ID"
        '
        'BntCancel
        '
        Me.BntCancel.Location = New System.Drawing.Point(301, 228)
        Me.BntCancel.Name = "BntCancel"
        Me.BntCancel.Size = New System.Drawing.Size(75, 23)
        Me.BntCancel.TabIndex = 25
        Me.BntCancel.Text = "Cancel"
        Me.BntCancel.UseVisualStyleBackColor = True
        '
        'BntSave
        '
        Me.BntSave.Location = New System.Drawing.Point(501, 228)
        Me.BntSave.Name = "BntSave"
        Me.BntSave.Size = New System.Drawing.Size(75, 23)
        Me.BntSave.TabIndex = 26
        Me.BntSave.Text = "Save"
        Me.BntSave.UseVisualStyleBackColor = True
        '
        'BntClose
        '
        Me.BntClose.Location = New System.Drawing.Point(621, 228)
        Me.BntClose.Name = "BntClose"
        Me.BntClose.Size = New System.Drawing.Size(75, 23)
        Me.BntClose.TabIndex = 27
        Me.BntClose.Text = "Close"
        Me.BntClose.UseVisualStyleBackColor = True
        '
        'TextBox4
        '
        Me.TextBox4.Location = New System.Drawing.Point(53, 12)
        Me.TextBox4.Name = "TextBox4"
        Me.TextBox4.Size = New System.Drawing.Size(100, 23)
        Me.TextBox4.TabIndex = 28
        '
        'IblRows
        '
        Me.IblRows.AutoSize = True
        Me.IblRows.Location = New System.Drawing.Point(53, 70)
        Me.IblRows.Name = "IblRows"
        Me.IblRows.Size = New System.Drawing.Size(79, 15)
        Me.IblRows.TabIndex = 29
        Me.IblRows.Text = "20  Rec of 100"
        '
        'BntImport
        '
        Me.BntImport.Location = New System.Drawing.Point(688, 27)
        Me.BntImport.Name = "BntImport"
        Me.BntImport.Size = New System.Drawing.Size(52, 35)
        Me.BntImport.TabIndex = 30
        Me.BntImport.Text = "Import"
        Me.BntImport.UseVisualStyleBackColor = True
        '
        'Semister
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(7.0!, 15.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(800, 450)
        Me.Controls.Add(Me.BntImport)
        Me.Controls.Add(Me.IblRows)
        Me.Controls.Add(Me.TextBox4)
        Me.Controls.Add(Me.BntClose)
        Me.Controls.Add(Me.BntSave)
        Me.Controls.Add(Me.BntCancel)
        Me.Controls.Add(Me.LabSID)
        Me.Controls.Add(Me.LabSC)
        Me.Controls.Add(Me.LabSN)
        Me.Controls.Add(Me.TextSID)
        Me.Controls.Add(Me.TextSCode)
        Me.Controls.Add(Me.TextBSName)
        Me.Controls.Add(Me.ListBox1)
        Me.Controls.Add(Me.btnNew)
        Me.Name = "Semister"
        Me.Text = "20  Rec of 100"
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub

    Friend WithEvents btnNew As Button
    Friend WithEvents ListBox1 As ListBox
    Friend WithEvents TextBSName As TextBox
    Friend WithEvents TextSCode As TextBox
    Friend WithEvents TextSID As TextBox
    Friend WithEvents LabSN As Label
    Friend WithEvents LabSC As Label
    Friend WithEvents LabSID As Label
    Friend WithEvents BntCancel As Button
    Friend WithEvents BntSave As Button
    Friend WithEvents BntClose As Button
    Friend WithEvents TextBox4 As TextBox
    Friend WithEvents IblRows As Label
    Friend WithEvents BntImport As Button
End Class
