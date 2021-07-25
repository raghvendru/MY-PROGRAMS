Public Class CCity
    Private _Code As String
    Private _Name As String
    Private _StateID As Integer
    Private _CityID As Integer

    Property StateID As Integer
        Get
            Return _StateID
        End Get
        Set
            _StateID = Value
        End Set
    End Property

    Property CityID As Integer
        Get
            Return _CityID
        End Get
        Set
            _CityID = Value
        End Set
    End Property

    Property Name As String
        Get
            Return _Name
        End Get
        Set
            _Name = Value
        End Set
    End Property

    Property Code As String
        Get
            Return _Code
        End Get
        Set
            _Code = Value
        End Set
    End Property
End Class
