public class Business extends LoanP implements Generated {
    
    private Business(String number, String name, double loanamout, int years, String typeofloan){
        setloanamount();
    }

    private void setloanamount() {
    }

    @Override
    public void generateTable() {
        double loanamount = 0;
        double monthlyrate = 0 ;
        double termInMonths = 0;

        double monthlyPayment = (loanamount * monthlyrate)/
                (1-Math.pow(1+monthlyrate, - termInMonths));
        
        
        
    }
}
