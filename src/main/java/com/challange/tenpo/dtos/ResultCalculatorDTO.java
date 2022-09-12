package com.challange.tenpo.dtos;

import static java.lang.Double.compare;

public class ResultCalculatorDTO {

    private Double result;

	public ResultCalculatorDTO(Double result) {
		super();
		this.result = result;
	}
	
    public Double getResult() {
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
	}

	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ResultCalculatorDTO other = (ResultCalculatorDTO) obj;
        return (compare(other.result, this.result) == 0);
    }
}
