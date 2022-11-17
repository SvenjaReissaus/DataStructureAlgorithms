package me.aed.shared;

public interface IFormResult<TResult> {
    void action(final TResult result);
}
