package euclideanDistance;

public class euclideanDistance {
	
	
	public static double euclideanDistance(int[]a , int[] b) {
		double sum= 0;
		for(int i=0; i<a.length; i++) {
			double diff = a[i]- b[i];
			sum = sum+ (diff * diff);
		}
		return Math.sqrt(sum);
	}
	
	/*public static double euclideanDistance(double x1, double y1 , double z1, double x2, double y2, double z2) {
		double diffx = x1 -x2;
		double diffy = y1 - y2;
		double diffz = z1 - z2;
		double sum = (diffx * diffx) + (diffy * diffy) + (diffz * diffz);
		
		return Math.sqrt(sum);
		
	}*/
	
	
	public static void main(String[] args) {
		// A(1,1)
		//B(5,4)
		// C(6,7)
		// ? (4,5)
		/*double ax = 1, ay = 1, az =2;
		double bx = 5, by = 4, bz= 3;
		double cx = 6, cy = 7, cz = 4;
		double dx= 4 ,  dy= 5, dz = 3; */
		int [] A= {0,1,2,3};
		int [] B = {4,5,6,7};
		int [] C = {2,2,3,3	};
		double distAB = euclideanDistance(A,B);
		double distAC = euclideanDistance(A,C);
		System.out.println("Distance A-B: " + distAB);
		System.out.println("Distance A-C: " + distAC);
		
		
		/*double distaA = euclideanDistance(ax , ay, az, dx, dy, dz );
		double distaB = euclideanDistance(bx , by, bz, dx, dy, dz);
		double distaC = euclideanDistance(cx, cy, cz, dx ,dy , dz);
		if(distaA < distaB && distaA < distaC) {
			System.out.println("Il punto D è più vicino a A");
		} else if(distaB < distaA && distaB < distaC) {
			System.out.println("Il punto D è più vicino a B");
		} else {
			System.out.println("Il putno D è più vicino a C");
		}*/
	}
}
