// @author A0315136M

public abstract class Value<T> {
  // Returning Sequential<T> causes us to not be able to access any methods like toString() as Sequential is private class
  public static <T> Value<T> sequential(T value) { // Must return Value<T> as it is public
    return new Sequential<T>(value);
  }

  public abstract T getIfTrueValue();
  
  public abstract T getIfFalseValue();

  public abstract boolean isSequential();
  
  public abstract String toString();

  public abstract <R> Value<R> map(Transformer<? super T, ? extends R> t);

  private static class Sequential<T> extends Value<T> {
    private T value;

    private Sequential(T value) {
      Sequential.this.value = value;
    }
    
    @Override
    public T getIfTrueValue() {
      return Sequential.this.value;
    }

    @Override
    public T getIfFalseValue() {
      return Sequential.this.value;
    }

    @Override
    public boolean isSequential() {
      return true;
    }

    public <R> Value<R> map(Transformer<? super T, ? extends R> t) {
      return Value.<R>sequential(t.transform(this.value));
    }

    @Override
    public String toString() {
      return Sequential.this.value.toString();
    }

  }

}
