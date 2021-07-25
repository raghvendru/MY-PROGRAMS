Imports System.IO
Imports System.Data
Public Class Import
    Public Property rows As DataGridViewRowCollection
    Private Sub Import_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        Dim fName As String = ""
        OpenFileDialog1.InitialDirectory = "c:\desktop"
        OpenFileDialog1.Filter = "CSV files (*.csv)|*.CSV"
        OpenFileDialog1.FilterIndex = 2
        OpenFileDialog1.RestoreDirectory = True
        If (OpenFileDialog1.ShowDialog() = Windows.Forms.DialogResult.OK) Then
            fName = OpenFileDialog1.FileName
        End If

        Dim TextLine As String = ""
        Dim SplitLine() As String
        Dim nLine = 0
        If System.IO.File.Exists(fName) = True Then
            gridImport.Columns.Add("Name", "Name")
            gridImport.Columns.Add("Code", "Code")
            Dim objReader As New System.IO.StreamReader(fName)
            Do While objReader.Peek() <> -1
                TextLine = objReader.ReadLine()
                If (nLine = 0) Then
                    Debug.Write("Header text" + TextLine)
                Else
                    SplitLine = Split(TextLine, ",")
                    Debug.Write(SplitLine.Count)
                    Me.gridImport.Rows.Add(SplitLine)
                End If
                nLine += 1
            Loop

        Else
            MsgBox("File Does Not Exist")
        End If
    End Sub

    Private Sub btnSave_Click(sender As Object, e As EventArgs) Handles btnSave.Click
        Me.rows = gridImport.Rows
        Me.DialogResult = Windows.Forms.DialogResult.OK
        Me.Close()
    End Sub

    Private Sub btnCancel_Click(sender As Object, e As EventArgs) Handles btnCancel.Click
        Me.rows = Nothing
        Me.DialogResult = Windows.Forms.DialogResult.Cancel
        Me.Close()
    End Sub
End Class