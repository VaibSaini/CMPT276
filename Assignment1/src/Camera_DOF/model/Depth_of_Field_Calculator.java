package Camera_DOF.model;

/**
 * Depth of Field calculator class used to calculate Depth of Field
 * It supports calculations for Hyperfocal point, Near Focalpoint,
 * Far Focalpoint, and Depth of field.
 */
public class Depth_of_Field_Calculator {
    private static final double COC = 0.029;    // "Circle of Confusion" for a "Full Frame" camera
    private final Lens ln;
    private final double Distance;
    private final double Aperture;

    public Depth_of_Field_Calculator(Lens ln, double aperture,double distance) {
        this.ln = ln;
        this.Distance = this.changetoMM(distance);
        this.Aperture = aperture;
    }
    // used to convert values from metres to millimetres
    public double changetoMM(double value){
        return value*1000;
    }
    // used to convert values from millimetres to metres
    public double changetoM(double value){
        return value*0.001;
    }

    public double Calc_Hyperfocal_Distance() {
        return this.changetoM(Math.pow(ln.getFocal_length(),2)/( Aperture * COC));
    }

    public double Calc_Near_Focalpoint(){
        double Hyper_focalpoint = this.changetoMM(this.Calc_Hyperfocal_Distance());
        return this.changetoM((Hyper_focalpoint * Distance) / (Hyper_focalpoint + (Distance - ln.getFocal_length())));
    }

    public double Calc_Far_Focalpoint(){
        double Hyper_focalpoint = this.changetoMM(this.Calc_Hyperfocal_Distance());
        if(Distance > Hyper_focalpoint){
            return Double.POSITIVE_INFINITY;
        } else {
            return this.changetoM((Hyper_focalpoint * Distance) / (Hyper_focalpoint - (Distance - ln.getFocal_length())));
        }
    }

    public double Calc_Depth_of_Field(){
        return this.changetoM(this.changetoMM(Calc_Far_Focalpoint()) - this.changetoMM(Calc_Near_Focalpoint()));
    }
}