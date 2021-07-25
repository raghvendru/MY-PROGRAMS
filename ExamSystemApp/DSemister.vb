Public Class DSemister
    Public Property SemisterIDs As Object

    Public Sub Add(Semister As CSemister)
        Dim param() As String = {"pName", "pCode", "pSemisterID"}
        Dim val() As String = {Semister.SemisterName, Semister.SemisterCode, Semister.SemisterID}

        DbAccess.Instance().ExcuteSPNQ("sSemisterAdd", param, val)
    End Sub

    Public Sub Update(Semister As CSemister)
        Dim dbAccess As DbAccess = DbAccess.Instance()
        Dim param() As String = {"pName", "pCode", "pSemisterID", "pSemisterID"}
        Dim val() As String = {Semister.SemisterName, Semister.SemisterCode, Semister.SemisterID.ToString, Semister.SemisterID.ToString}
        dbAccess.ExcuteSPNQ("sSemisterUpdate", param, val)
    End Sub
    Private Sub PopulateClass(Semister As CSemister, row As DataRow)
        Semister.SemisterCode = row.Item("Code")
        Semister.SemisterName = row.Item("Name")
        Semister.SemisterID = row.Item("SemisterID")

    End Sub
    Public Function GetRow(SemesterID As Integer) As CSemister
        Dim dbAccess As DbAccess = DbAccess.Instance()
        Dim param() As String = {"pSemesterID"}
        Dim val() As String = {SemesterID}
        Dim dt As DataTable = dbAccess.ExcuteSPDT("sSemesterGet", param, val)
        Dim Semister As CSemister = New CSemister()
        If (dt.Rows.Count = 1) Then
            PopulateClass(Semister, dt.Rows(0))
        End If
    End Function
    Public Function GetList(SemesterIDs As Integer) As List(Of CSemister)
        Dim dbAccess As DbAccess = DbAccess.Instance()
        Dim param() As String = {"pSemisterIDs"}
        Dim val() As String = {SemesterIDs.ToString}
        Dim dt = dbAccess.ExcuteSPDT("sSemisterGetList", param, val)
        Dim list As List(Of CSemister) = New List(Of CSemister)
        For Each row In dt.Rows
            Dim Semister = New CSemister()
            PopulateClass(Semister, row)
            list.Add(Semister)
        Next
        Return list
    End Function

    '    -- Get List by ids Or all
    'DROP PROCEDURE If EXISTS sSemesterGetList;
    'CREATE PROCEDURE sSemesterGetList
    '  (
    '    IN pSemesterIDs TEXT
    '  )
    '  BEGIN
    '    Select Case
    '      SemesterID,
    '      Name,
    '      Code
    '    FROM tSemester
    '    WHERE
    '      Case WHEN pSemesterIDs = '' OR pSemesterIDs IS NULL
    '        THEN
    '          1 = 1
    '      Else
    '        find_in_set(SemesterID, pSemesterIDs)
    '      End;
    '  End;

    '-- Get with Page / Filters


    'in database u given semester show wait ill be back do changes in here
    Public Function BSemisterGetList(pSemisterIDs As Integer) As List(Of CSemister)
        Dim dbAccess As DbAccess = DbAccess.Instance()
        Dim param() As String = {"pSemisterIDs"}
        Dim val() As String = {SemisterIDs.ToString}
        Dim dt = dbAccess.ExcuteSPDT("sStateGetList", param, val)
        Dim list As List(Of CSemister) = New List(Of CSemister)
        For Each row In dt.Rows
            Dim state = New CSemister()
            PopulateClass(state, row)
            list.Add(state)
        Next
        Return list
        'Public Function GetListPage(SemisterName As String, Code As String, SemisterID As Integer, PageNum As Integer, PageSize As Integer) As DSClass
        '    Dim dbAccess As DbAccess = DbAccess.Instance()
        '    Dim param() As String = {"pSemisterName", "pCode", "pSemisterID", "pPageNum", "pPageSize"}
        '    Dim val() As String = {SemisterName, Code, SemisterID.ToString, PageNum.ToString, PageSize.ToString}
        '    Dim ds = dbAccess.ExcuteSPDS("sSemisterGetListPage", param, val)
        '    Dim retObj As DSemister DSClass = New DSemister Class()
        '    retObj.numRows = ds.Tables(0).Rows(0).Item(0)
        '    Dim dt = ds.Tables(1)
        '    ' convert the DATATABLE to list of CSemisterobjects
        '    Dim list As List(Of CSemister) = New List(Of CSemister)
        '    For Each row In dt.Rows
        '        Dim Semister = New CSemister()
        '        PopulateClass(Semister, row)
        '        list.Add(Semister)
        '    Next
        '    retObj.obj = CType(list, Object)
        '    Return retObj
    End Function

End Class
