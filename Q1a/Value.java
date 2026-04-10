// @author A0315136M

abstract class Value<T> {

  public static <T> Value<T> sequential(T value) { // Must retrn Value<T> as it is public
    return new Sequential<T>(value);
  }

  public abstract T getIfTrueValue();
  
  public abstract T getIfFalseValue();

  public abstract boolean isSequential();

  public abstract String toString();

  private static class Sequential<T> extends Value<T> {
    private T value;

    private Sequential(T value) {
      Sequential.this.value = value;
    }
    
    @Override
    public T getIfTrueValue() {
      return this.value;
    }

    @Override
    public T getIfFalseValue() {
      return Sequential.this.value;
    }

    @Override
    public boolean isSequential() {
      return true;
    }

    @Override
    public String toString() {
      return Sequential.this.value.toString();
    }

  }

}
