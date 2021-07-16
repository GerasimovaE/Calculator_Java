public class Calculation {

    private SuperCalculator superCalculator;
    private Action action;

    public Calculation(SuperCalculator superCalculator) {
        this.superCalculator = superCalculator;
        this.action = superCalculator.getAction();
    }

    public void calculate() throws CalculatorExeption {
        if (superCalculator.getA()>10 || superCalculator.getB()>10){
            throw new CalculatorExeption("The entered values are greater than 10.");
        }

        if (action == Action.ADDITION){
            superCalculator.setResult(superCalculator.getA() + superCalculator.getB());
        }

        if (action == Action.SUBTRACTION){
            superCalculator.setResult(superCalculator.getA() - superCalculator.getB());
        }

        if (action == Action.MULTIPLICATION){
            superCalculator.setResult(superCalculator.getA() * superCalculator.getB());
        }

        if (action == Action.DIV){
            superCalculator.setResult(superCalculator.getA() / superCalculator.getB());
        }
    }


}
