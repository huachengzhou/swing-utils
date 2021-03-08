package tool.utils.entity;

import com.aspose.words.Cell;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * @Author noatnu
 * @Description
 * @createDate 2019/5/21
 **/
public class MergeCellModel implements Serializable {
    private Integer startRowIndex;

    private Integer startColumnIndex;

    private Integer endRowIndex;

    private Integer endColumnIndex;

    private Cell cellStartRange;
    private Cell cellEndRange;


    public MergeCellModel(Integer startRowIndex, Integer startColumnIndex, Integer endRowIndex, Integer endColumnIndex) {
        this.startRowIndex = startRowIndex;
        this.startColumnIndex = startColumnIndex;
        this.endRowIndex = endRowIndex;
        this.endColumnIndex = endColumnIndex;
    }

    public MergeCellModel(Cell cellStartRange, Cell cellEndRange) {
        this.cellStartRange = cellStartRange;
        this.cellEndRange = cellEndRange;
    }

    private MergeCellModel(){}

    public Integer getStartRowIndex() {
        return startRowIndex;
    }

    public Integer getStartColumnIndex() {
        return startColumnIndex;
    }

    public Integer getEndRowIndex() {
        return endRowIndex;
    }

    public Integer getEndColumnIndex() {
        return endColumnIndex;
    }

    public Cell getCellStartRange() {
        return cellStartRange;
    }

    public void setCellStartRange(Cell cellStartRange) {
        this.cellStartRange = cellStartRange;
    }

    public Cell getCellEndRange() {
        return cellEndRange;
    }

    public void setCellEndRange(Cell cellEndRange) {
        this.cellEndRange = cellEndRange;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        MergeCellModel that = (MergeCellModel) o;

        return new EqualsBuilder()
                .append(startRowIndex, that.startRowIndex)
                .append(startColumnIndex, that.startColumnIndex)
                .append(endRowIndex, that.endRowIndex)
                .append(endColumnIndex, that.endColumnIndex)
                .append(cellStartRange, that.cellStartRange)
                .append(cellEndRange, that.cellEndRange)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(startRowIndex)
                .append(startColumnIndex)
                .append(endRowIndex)
                .append(endColumnIndex)
                .append(cellStartRange)
                .append(cellEndRange)
                .toHashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MergeCellModel{");
        sb.append("startRowIndex=").append(startRowIndex);
        sb.append(", startColumnIndex=").append(startColumnIndex);
        sb.append(", endRowIndex=").append(endRowIndex);
        sb.append(", endColumnIndex=").append(endColumnIndex);
        sb.append(", cellStartRange=").append(cellStartRange);
        sb.append(", cellEndRange=").append(cellEndRange);
        sb.append('}');
        return sb.toString();
    }
}