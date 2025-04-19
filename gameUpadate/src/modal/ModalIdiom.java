package modal;

public class ModalIdiom {

    private String idiom;
    private String definition;

    public String getIdiom() {
        return idiom;
    }

    public void setIdiom(String idiom) {
        this.idiom = idiom;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    @Override
    public String toString() {
        return "Idiom: " + idiom + "\nDefinition: " + definition;
    }
}
