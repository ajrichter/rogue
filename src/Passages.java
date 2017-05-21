

/* Stolen Rogue C code
 * static struct rdes
    {
	bool	conn[MAXROOMS];		
	bool	isconn[MAXROOMS];	
	bool	ingraph;		
	{ { 0, 1, 2, 3, 4, 5, 6, 7, 8 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 0 },
    } rdes[MAXROOMS] = {
	{ { 0, 1, 0, 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 0 },
	{ { 1, 0, 1, 0, 1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 0 },
	{ { 0, 1, 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 0 },
	{ { 1, 0, 0, 0, 1, 0, 1, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 0 },
	{ { 0, 1, 0, 1, 0, 1, 0, 1, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 0 },
	{ { 0, 0, 1, 0, 1, 0, 0, 0, 1 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 0 },
	{ { 0, 0, 0, 1, 0, 0, 0, 1, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 0 },
	{ { 0, 0, 0, 0, 1, 0, 1, 0, 1 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 0 },
	{ { 0, 0, 0, 0, 0, 1, 0, 1, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 0 },
    };
 */

public class Passages {
	protected final int MAXROOMS = 9;

	class Rconn {
		boolean[] conn, isconn;
		boolean ingraph;

		public Rconn() {
			conn = new boolean[MAXROOMS];		/* possible to connect to room i? */
			isconn = new boolean[MAXROOMS];		/* connection been made to room i? */
			ingraph = false;	 	/* this room in graph already? */
		}
	}

	public Passages(){
		Rconn[] rdes = new Rconn[MAXROOMS];
		/* Initialize Rooms able to Connect*/
		rdes[0].conn[1] = rdes[0].conn[3] = true;
		rdes[1].conn[0] = rdes[1].conn[2] = rdes[1].conn[4] = true; 
		rdes[2].conn[1] = rdes[2].conn[5] = true;
		rdes[3].conn[0] = rdes[3].conn[4] = rdes[3].conn[6] = true;
		rdes[4].conn[1] = rdes[4].conn[3] = rdes[4].conn[5] = rdes[4].conn[7] =  true;
		rdes[5].conn[2] = rdes[5].conn[4] = rdes[5].conn[8] = true;
		rdes[6].conn[3] = rdes[6].conn[7] = true;
		rdes[7].conn[4] = rdes[7].conn[6] = rdes[7].conn[8] = true;
		rdes[8].conn[5] = rdes[8].conn[7] = true;
	}

	public void makePass(char[] floor, Room[] rooms, boolean[] roombools){

	}
}
