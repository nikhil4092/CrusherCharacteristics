package project.mineralprocessing;

/**
 * Created by nikhilkumar on 25/03/16.
 */
public class CrusherCharacter {

    float inputsize,outputsize,cost,reductionratio,production,power;
    String name;
    public void setName(String name){this.name=name;}
    public void setInputsize(Float inputsize){this.inputsize=inputsize;}
    public void setOutputsize(Float outputsize){this.outputsize=outputsize;}
    public void setCost(Float cost){this.cost=cost;}
    public void setReductionratio(Float reductionratio){this.reductionratio=reductionratio;}
    public void setProduction(Float production){this.production=production;}
    public void setPower(Float power){this.power=power;}

    public String getName(){return name;}
    public Float getInputsize(){return inputsize;}
    public Float getOutputsize(){return outputsize;}
    public Float getCost(){return cost;}
    public Float getReductionratio(){return reductionratio;}
    public Float getProduction(){return production;}
    public Float getPower(){return power;}

}
