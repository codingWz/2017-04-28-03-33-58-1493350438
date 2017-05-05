public class BowlingGame {

    public int getBowlingScore(String bowlingCode) {
       
		int score = 0;
		String[] input = bowlingCode.split("\\|\\|");
		String[] common = input[0].split("\\|");
		String[] revise;
		
		//has extras chance
		if (input.length > 1) {
			
			String last = input[1];			
			revise = new String[common.length + last.length()];
			
			for (int i = 0;i < common.length;i ++) {
				revise[i] = common[i];
			}
			
			StringBuilder sb1 = new StringBuilder(last);
			if (last.length() == 1) {
				if (!last.equals("X")) {
					sb1.append("-");
					revise[10] = sb1.toString();
				}
			}else {
				StringBuilder sb2 = new StringBuilder();
				sb1 = new StringBuilder(last.substring(0,1));
				sb2 = new StringBuilder(last.substring(1,2));
				
				if (!sb1.toString().equals("X")) {
					revise[10] = last;
				}else {
					revise[10] = "X";
					if (!sb2.toString().equals("X")) {
						sb2.append("-");
					}
					revise[11] = sb2.toString();
				}
					
			}
			
		}else {
			revise = common;
		}
		
		for (int i = 0;i < 10;i ++) {
			//match strike
			if (revise[i].length() == 1) {
				score += 10;
				if (revise[i + 1].length() == 1) {
					score += 10;
					if (revise[i + 2].length() == 1) {
						score += 10;
					}else {
						score += addExtra1(revise[i + 2]);
					}
				}else {
					score += addExtra2(revise[i + 1]);
				}
			}else {
				//match spare
				if (revise[i].contains("/")) {
					score += 10;
					if (revise[i + 1].length() == 1) {
						score += 10;
					}else {
						score += addExtra1(revise[i + 1]);
					}
				}else {//match common case
					score += addExtra2(revise[i]);
				}
			}
		}
		
		return score;
	
    }
	private int addExtra1(String s) {
		int score = 0;
		char num = s.charAt(0);
		if (num != '-') {
			score += (num - 48);
		}
		return score;
	}
	
	private int addExtra2(String s) {
		int score = 0;
		if (s.contains("/")) {
			score += 10;
		}else {
			char num1 = s.charAt(0);
			char num2 = s.charAt(1);
			
			if (num1 != '-') {
				score += (num1 - 48);
			}
			
			if (num2 != '-') {
				score += (num2 - 48);
			}
		}
		return score;
	}
}
