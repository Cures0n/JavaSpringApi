package klimov.test.spring.models;

public class Count {
    private String symbol;

    public Count(){
    }

    public Count(String symbol){
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
