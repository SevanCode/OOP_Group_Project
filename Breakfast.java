public class Breakfast extends DigitalizedMenu{
    // english bre
    // pancake
    // waffle
    private final String[] options = {
            "english_breakfast",
            "pancake",
            "waffle"
    };
    // we should change the type into object because each option has their own ingredients

    public String whichOptionSelectedFromBreakfast(String[] arr){
        if (!isBeverage()){
            printArray(arr);
        }
        return null;
    }

}
