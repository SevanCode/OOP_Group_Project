public class DigitalizedMenu {
    private boolean optionSelector;
    public DigitalizedMenu(boolean optionSelector){
        setOptionSelector(optionSelector);
    }
    public DigitalizedMenu(){}
    public void setOptionSelector(boolean optionSelector){
        this.optionSelector = optionSelector;
    }
    public boolean getOptionSelector(){
        return this.optionSelector;
    }
    // options for breakfast
    // options for beverages
    private static final String BACKGROUND = "green";
    // future change to int

    // methods to find out which option is selected
//    public DigitalizedMenu(){}
//    public DigitalizedMenu(int breakfast, int beverages){
//
//    }
    public boolean isBeverage(){
        if (getOptionSelector()) return true;
        return false;
    }

    public void printArray(String [] arr){
        for(String options : arr) System.out.println(options);
    }
}
