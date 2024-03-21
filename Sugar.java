public class Sugar {
    private int amount;
    public Sugar(int amount){
        setAmount(amount);
    }
    public void setAmount(int amount){
        this.amount = amount;
    }
    public int getAmount(){
        return this.amount;
    }
    public boolean check(int amount){
        if (amount <= 3 && amount>=0) return true;
        return false;
    }
    public int returnsAmountOfs(){
        if (check(getAmount())) return getAmount();
        return -1;
    }
}
