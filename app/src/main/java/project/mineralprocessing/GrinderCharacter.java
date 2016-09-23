package project.mineralprocessing;

/**
 * Created by nikhilkumar on 25/03/16.
 */
public class GrinderCharacter {

    String inputsize,outputsize,cost,reductionratio,runningcost,maintainancecost,spareparts;
    String name;
    public void setName(String name){this.name=name;}
    public void setInputsize(String inputsize){this.inputsize=inputsize;}
    public void setOutputsize(String outputsize){this.outputsize=outputsize;}
    public void setCost(String cost){this.cost=cost;}
    public void setReductionratio(String reductionratio){this.reductionratio=reductionratio;}
    public void setRunningcost(String runningcost){this.runningcost=runningcost;}
    public void setMaintainancecost(String maintainancecost){this.maintainancecost=maintainancecost;}
    public void setSpareparts(String spareparts){this.spareparts=spareparts;}

    public String getName(){return name;}
    public String getInputsize(){return inputsize;}
    public String getOutputsize(){return outputsize;}
    public String getCost(){return cost;}
    public String getReductionratio(){return reductionratio;}
    public String getRunningcost(){return runningcost;}
    public String getMaintainancecost(){return maintainancecost;}
    public String getSpareparts(){return spareparts;}

}
