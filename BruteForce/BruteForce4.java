
public class BruteForce4 {
	static int[] nums = {1,2,3,4};
	// Generare tutte le combinazioni di 2 numeri senza ripetizioni
	public static void bruteForce(int[] nums, int[]path,boolean[] used, int level) {
		if(level ==2) {
			System.out.println(path[0] +" - "+ path[1]);
			return;
		}
		System.out.println();
		
		for(int i=0;i<nums.length;i++) {
			if(!used[i]) {
				path[level] = nums[i];
				used[i]= true;
				bruteForce(nums, path, used, level + 1); // ricorsione
				used[i]= false;
			}
		}
	}
	public static void main(String[]args) {
		int[] path = new int[2];
		boolean[]used = new boolean[nums.length];
		bruteForce(nums,path,used,0);
	}
}

