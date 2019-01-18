package enums;

public enum GeneroEnum {

    TERROR("Terror"),
    ACAO("Ação"),
    KIDS("Kids"),
    RELIGIAO("Religião");


    private String tipo;

    private GeneroEnum(String tipo) {

        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "Genero{" +
                "tipo='" + tipo + '\'' +
                '}';
    }
}
