package it.agtonybarletta.codechallrunner.reader;

public class ReaderOutput<T> {
    public String terminator;
    public T data;

    public String getTerminator() {
        return terminator;
    }

    public void setTerminator(String terminator) {
        this.terminator = terminator;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
