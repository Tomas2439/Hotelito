class pasajero{
    private String dni;
    private String nombre;
    private String email;
    private String telefono;

    public pasajero(String dni, String nombre, String email, String telefono){
        this.dni = dni;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override 
    public String toString(){
        return "Dni: " + dni + "\nNombre: "+nombre+"\nEmail: "+email+"\nTelefono: "+telefono;
    }
}