package desafio.spring.desafiodetransacao.dto;

import java.util.DoubleSummaryStatistics;

public class StatisticsResponse {

    private long count;
    private double sum;
    private double avg;
    private double min;
    private double max;

    public StatisticsResponse(DoubleSummaryStatistics states) {
        this.avg = states.getAverage();
        this.count = states.getCount();
        this.min = states.getMin();
        this.sum = states.getSum();
        this.max = states.getMax();
    }

    public long getCount() {
        return count;
    }

    public double getSum() {
        return sum;
    }

    public double getAvg() {
        return avg;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }
}
