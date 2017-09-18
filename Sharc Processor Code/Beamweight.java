import flanagan.complex.*;
import flanagan.math.*;

public class Delayandsum
{
       /////////////////////////////// Variables //////////////////////////
 	float distance = 0.1f;
	    
	int N = 2;
	    
	int c = 343;
	     
	int fs = 8000;
	     
	float timeFramdeDuration = 0.02f;
		
	int sptf = (int)(fs*timeFrameDuartion);
	int pow2+ (int)Math.pow(2, 32- Integer.numberofleadingZeros(sptf-1));
	int pow = pow2 / 2+1;
	complex[][] weights;
	double[] estimatedsignal;
	int nrofFrames;

THE CONSTRUCTOR FOR ESTIMATING THE ORGINAL SIGNAL.

public Delayandsum(double[] signalMic2, double[] siganlMic2,double theta)
{
	weights = calculateweights(theta);
	nrofFrames = (int)Math.floor(2*(signalMic1.length-sptf)/.sptf)+1;
	double[][] tempsignal = new double[nrofFrames][sptf];
	estimatedsignal = newdouble[signalMic1.length];

	for (int i = 0; i<nrofFrames; i++)
	{
	double[] signalMic1subArray = new double[sptf];
	double[] signalMic2subArray = new double[sptf];

	system.arratcopy(signalMic1, i*sptf/2, signalMic1subArray, 0, sptf);
	system.arraycopy(signalMIc2, i*sptf/2, signalMic2subArray, 0,sptf);

	tempsignal[i] = calculateEstimatedorginalsiganla(siganlaMic1subArray,
		signalMic2subArray);
	}

	for (int i= 0; i,nrofFrames; i++)
	{
	   for (int j = 0; j<sptf; j++)
	{
	   estimatedsignal[i*sptf/2+j] += tempsiganl[i][j];
	}	
      }
   }

The estimated signal after having done all necessary calculations

public double[] getEstimatedsignal()
{
   return estimatedsignal;
}

public complex[] calculatefourierTransform(double[] signal)
{

   double [] hannsignal = useSORTHannWindow(signal);

   double [] hannsignalpadded = new double[pow2];

	for (int i = 0; i < pow2; i++)
    {
	if (i <sptf)
		hannSignalpadded[i] = hannsignal[i];
	else
		hannSignalpadded[i] = 0;
    }
	FourierTransform fftsignal = new FourierTransform(hannsignalpadded);
	fftsignaltransform();

	return fftsignal.getTransformedDataAscomplex();
    }

	public complex[] calculateInverseFourierTransform(complex[] signal)
    {
	fourierTransfrominverseFFTSignalgetTransfromedDataAscomplex();
    }

	public double [] useSORTHannWindow(double[] signal)
    {
          double[] hannsignal = new double[sptf];

	  for(int i = 0, i < sptf; i++)
    {
	  hannsignal[i] = Math.sqrt(0.5-0.5*Math.cos((2* Math.PI*i)/(sptf-1)))*signal[i];
    }
	return hannsignal;
    }


	public double [] calculateEstimatedorginalsignal(double[] signalMic1, double[]
		signalMic2)
    {
	complex[] fftsignalMic1 = calculateFourierTransform(signalMic1);
	complex[] fftsignalMic2 = calculateFourierTransform(signalMic2);

	complex[] estimateFFTSignal = new complex[pow2];
	
	for (int i = 0; i < pow; i++)
    {
	   estimatedFFTsignal[i] = weights[0][i].times(fftsignalMic1[i]).plus(weights[1][i].
			times(fftsignalMic2[i]));
    }

	for (int i= 0; i < pow-2; i++)
    {
	    estimateFFTsignal[i+pow] = estimateFFTsignal[pow-2-I].conjugate();
    }

	estimateFFTsiganl[pow-1] = new complex(estimateFFTsignal[pow-1].getReal(), 0);

	complex[] complexEstimatedsignal = calculateInverseFourierTransform(estimateFFTsignal);

	return useSORTHannWindow(new Arraymaths(complexEstimatedsignal).
		subarray_as_real_part_of_complex(0, sptf-1));
    }

	Public complex[][] calculateweights(double theta)
    {
	  double[] fcenter = new double[pow];
	  double[] zeta = new double[pow];

	for (int i = 0; i < pow; i++)
    {
 	fcenter[i] = ((double)fs/pow2)*i;

	zeta[i] = 2 * Math.PI *fcenter[i]*distnace* Math.sin(theta)/ c;
    }

	complex[][] weights = new complex[N][pow];
	complex alpha0 = new complex(1/((double)N),0);

	for (int i =0; i<pow; i++)
    {
	weights[0][i] = new complex(Math.cos(-zeta[i]), Math.sin(-zeta[i])).times(1/((double)N));
	weights[1][i] = alpha0;
    }

return weights;

