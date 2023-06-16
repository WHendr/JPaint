package design.lists;

public interface ISubject {
	 void registerObservers(IObserver observer);
	 void unregisterObserver(IObserver observer);
	 void notifyObservers();
}
