package lab07;

public class BetterRandomNumberGenerator implements RandomNumberGenerator {

	private long const1 = 1000;
	private long const2 = 1234567;
	private long m = 15485867;
	private long seed;
	
	@Override
	public int nextInt(int max) {
		seed = (const1*seed+const2) % m;
		return (int) (seed % max);
	}

	@Override
	public void setSeed(long seed) {
		this.seed = seed;
	}

	@Override
	public void setConstants(long const1, long const2) {
		this.const1 = const1;
		this.const2 = const2;
	}

}
