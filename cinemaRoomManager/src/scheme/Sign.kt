package cinema.scheme

class Sign(display:String = "0") : Scheme() {
    override var display = display
        get() {
            return if(field == "0") " " else field
        }
}