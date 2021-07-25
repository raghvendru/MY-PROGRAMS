Public Class CState
    Private _Name As String
    Private _Code As String
    Private _ID As Integer
    Public Sub New()
    End Sub
    Public Sub New(ID As Integer, name As String, code As String)
        Me.ID = ID
        Me.Name = name
        Me.Code = code
    End Sub
    Public Sub New(name As String, code As String)
        Me.Name = name
        Me.Code = code
    End Sub

    Public Property ID As Integer
        Get
            Return _ID
        End Get
        Set
            _ID = Value
        End Set
    End Property

    Public Property Name As String
        Get
            Return _Name
        End Get
        Set
            _Name = Value
        End Set
    End Property

    Public Property Code As String
        Get
            Return _Code
        End Get
        Set
            _Code = Value
        End Set
    End Property



End Class
