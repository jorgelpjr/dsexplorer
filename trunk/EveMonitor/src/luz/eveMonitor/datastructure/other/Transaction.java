package luz.eveMonitor.datastructure.other;

import luz.eveMonitor.entities.eveMon.Order;

public class Transaction implements Comparable<Transaction>{
	public Order buy;
	public Order sell;
	public double win;
	public long items;
	
	public Transaction(Order buy, Order sell) {
		this.buy=buy;
		this.sell=sell;
	}
	
	public long getMaxItemNumber(double volume, double money){
		double maxBuy  = buy.getVolRem();
		double maxSell = sell.getVolRem();		
		double maxVol   = volume / buy.getType().getVolume();
		double maxPrice = money  / buy.getPrice();
		
		double items=Math.min(maxBuy, maxSell);
		items=Math.min(items, maxVol);
		items=Math.min(items, maxPrice);		
		return (long)items;
	}
	
	public double calcWin(TransactionSettings settings) {
		return calcWin(settings.getMaxMoney(), settings.getMaxVolume(), settings.getAccounting(), settings.getSecurity());		
	}
	
	public double calcWin(double money, double volume, int accounting, double security){
		if(buy.getSystem().getSecurity()<security || sell.getSystem().getSecurity()<security){
			win=-999999999;
		}else{	
			items = getMaxItemNumber(volume, money);
			win=(items*sell.getPrice())*(1-(10-accounting)/1000d)-items*buy.getPrice();
		}
		return win;
	}
	
	public double getVolume(){
		return buy.getType().getVolume();
	}
	
	@Override
	public int compareTo(Transaction that) {
		double diff=that.win-this.win;
		if(diff>0) 
			return 1;
		else if (diff<0) 
			return -1;
		return 0;
	}

	
	//Object///////////////////////////////////////////////
	
	
	private Object[] getSignificantFields(){
		return new Object[] {buy, sell};
	}
	
	@Override
	public int hashCode() {
		int hash = 0;
		for (Object o : getSignificantFields()) {
			hash = 31*hash+o.hashCode();
		}
		return hash;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o==null)
			return false;
		
		if(!(o instanceof Transaction))
			return false;		
		Transaction that=(Transaction)o;
		
		Object[] thisFields=this.getSignificantFields();
		Object[] thatFields=that.getSignificantFields();
		for (int i = 0; i < thisFields.length; i++) {
			if(!areEqual(thisFields[i], thatFields[i]))
				return false;
		}
		
		return true;
	}
	
	static public boolean areEqual(Object aThis, Object aThat){
		return aThis == null ? aThat == null : aThis.equals(aThat);
	}





	
}