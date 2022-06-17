package com.vehicle.rent.rental.model;

public class Slot<T> {
    private T startTime;
    private T endTime;

    public static <T> Slot.SlotBuilder<T> builder() {
        return new Slot.SlotBuilder();
    }

    public T getStartTime() {
        return this.startTime;
    }

    public T getEndTime() {
        return this.endTime;
    }

    public void setStartTime(final T startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(final T endTime) {
        this.endTime = endTime;
    }

    public String toString() {
        return "Slot(startTime=" + this.getStartTime() + ", endTime=" + this.getEndTime() + ")";
    }

    public Slot(final T startTime, final T endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Slot() {
    }

    public static class SlotBuilder<T> {
        private T startTime;
        private T endTime;

        SlotBuilder() {
        }

        public Slot.SlotBuilder<T> startTime(final T startTime) {
            this.startTime = startTime;
            return this;
        }

        public Slot.SlotBuilder<T> endTime(final T endTime) {
            this.endTime = endTime;
            return this;
        }

        public Slot<T> build() {
            return new Slot(this.startTime, this.endTime);
        }

        public String toString() {
            return "Slot.SlotBuilder(startTime=" + this.startTime + ", endTime=" + this.endTime + ")";
        }
    }
}
