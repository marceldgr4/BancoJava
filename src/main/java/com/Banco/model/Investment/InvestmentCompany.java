package com.Banco.model.Investment;

public class InvestmentCompany {
    private static  int companyCount = 0;

    private final String code;
    private  String name;
    private double returnPercentage;
    private int riskLevel;
    private double reliability;

    public InvestmentCompany(String code, String name, double returnPercentage, int riskLevel, double reliability) {
        validateCode(code);
        validateName(name);
        validateRiskLevel(riskLevel);
        validateReliability(reliability);
        this.code = code;
        this.name = name;
        this.returnPercentage = returnPercentage;
        this.riskLevel = riskLevel;
        this.reliability = reliability;
        companyCount++;
    }
    public String getRiskDescription(){
        return switch (riskLevel){
            case 1 -> "Very Low";
            case 2 -> "Low";
            case 3 -> "Medium";
            case 4 -> "High";
            case 5 -> "Very High";
            default -> "Unknown";
        };
    }
    public static int getCompanyCount(){
        return companyCount;
    }
    static void resetCompanyCount(){
        companyCount = 0;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getReturnPercentage() {
        return returnPercentage;
    }

    public int getRiskLevel() {
        return riskLevel;
    }

    public double getReliability() {
        return reliability;
    }

    public void setName(String name) {
        validateName(name);
        this.name = name;
    }


    public void setReturnPercentage(double returnPercentage) {
        this.returnPercentage = returnPercentage;
    }

    public void setRiskLevel(int riskLevel) {
        validateRiskLevel(riskLevel);
        this.riskLevel = riskLevel;
    }

    public void setReliability(double reliability) {
        validateReliability(reliability);
        this.reliability = reliability;
    }

    private static void validateCode(String code){
        if(code == null ||code.isBlank())
            throw new IllegalArgumentException("Company code must not be blank");
    }
    private static void validateName(String name){
        if(name == null || name.isBlank())
            throw new IllegalArgumentException("Company name must not be blank");
    }
    private static void validateRiskLevel(int level){
        if(level < 1 || level > 5)
            throw new IllegalArgumentException("Company risk level must be between 1 and 5 got"+ level);
    }
    private static void validateReliability(double value){
        if(value < 0.0 || value > 1.0)
            throw new IllegalArgumentException("Company reliability must be between 0.0 and 1.0 got"+ value);
    }
    @Override
    public String toString(){
        return String.format("InvestmentCompany[code=%s, name=%s, return=%.1f%%, risk=%s, reliability=%.0f%%]",
                code, name, returnPercentage * 100, getRiskDescription(), reliability * 100);
    }

}
