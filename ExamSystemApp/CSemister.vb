Public Class CSemister
    Private _SemisterName As String
    Private _SemisterCode As String
    Private _SemisterID As Integer

    Property SemisterName As String
        Get
            Return _SemisterName
        End Get
        Set
            _SemisterName = Value
        End Set
    End Property

    Property SemisterCode As String
        Get
            Return _SemisterCode
        End Get
        Set
            _SemisterCode = Value
        End Set
    End Property

    Property SemisterID As Integer
        Get
            Return _SemisterID
        End Get
        Set
            _SemisterID = Value
        End Set
    End Property
End Class
