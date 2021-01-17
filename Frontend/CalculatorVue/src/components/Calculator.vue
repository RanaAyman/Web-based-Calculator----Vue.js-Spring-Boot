<template>
  <div class="container">
    <div class="calculator">
      <div class="answer">{{ answer||'0' }}</div>
      <div class="display">{{previous+operator+current}}</div>

      <div @click="percent" id="percent" class="btn operator">%</div>
      <div @click="ce" id="ce" class="btn operator">CE</div>
      <div @click="clear" id="clear" class="btn operator">C</div>
      <div @click="remove" id="remove" class="btn operator">DEL</div>

      <div @click="OneOverX" id="OneOverX" class="btn operator">
        <sup>1</sup>&#8725;
        <sub>x</sub>
      </div>
      <div @click="Square" id="Square" class="btn operator">
        <span class=sup>x
        <sup>2</sup> </span>
      </div>
      <div @click="Root" id="Root" class="btn operator">&#8730;</div>
      <div @click="operation('/')" id="divide" class="btn operator">÷</div>

      <div @click="append('7')" id="n7" class="btn">7</div>
      <div @click="append('8')" id="n8" class="btn">8</div>
      <div @click="append('9')" id="n9" class="btn">9</div>
      <div @click="operation('*')" id="times" class="btn operator">*</div>

      <div @click="append('4')" id="n4" class="btn">4</div>
      <div @click="append('5')" id="n5" class="btn">5</div>
      <div @click="append('6')" id="n6" class="btn">6</div>
      <div @click="operation('-')" id="minus" class="btn operator">-</div>

      <div @click="append('1')" id="n1" class="btn">1</div>
      <div @click="append('2')" id="n2" class="btn">2</div>
      <div @click="append('3')" id="n3" class="btn">3</div>
      <div @click="operation('+')" id="plus" class="btn operator">+</div>

      <div @click="sign" id="sign" class="btn">+/-</div>
      <div @click="append('0')" id="n0" class="btn">0</div>
      <div @click="dot" id="dot" class="btn">.</div>
      <div @click="equal" id="equal" class="btn operator">=</div>
    </div>
  </div>
</template>


 <script>
import axios from "axios";
export default {
  data() {
    return {
      current: "",
      previous: "",
      answer: "",
      operatorClicked: false,
      operator: "",
      flag: false,
      equalPressed: false
    };
  },
  methods: {
    append(number ) {
      this.current = `${this.current}${number}`;
    },
    clear() {
      this.current = "";
      this.answer = "";
      this.previous = "";
      this.operatorClicked = false;
      this.operator = "";
      this.flag = false;
      this.equalPressed = false;
    },
    ce() {
      this.current = "";
      this.answer = "";
      this.previous = "";
      this.operatorClicked = false;
      this.operator = "";
      this.flag = false;
      this.equalPressed = false;
    },
    remove() {
      if (!isNaN(this.current)) {
        if (this.current != "") {
          this.current = this.current.slice(0, -1);
          if (this.current == "-") this.current = "";
        } else if (this.operator != "") {
          this.operator = "";
          this.operatorClicked = false;
          this.current = this.previous;
          this.previous="";
        } 
        // else if (this.previous != "") {
        //   this.previous = this.previous.slice(0, -1);
        // }
      }
    },
    sign() {
      if (!this.current=="") {
        this.current =
          this.current.charAt(0) === "-"
            ? this.current.slice(1)
            : `-${this.current}`;
      }
    },
    dot() {
      if (this.current.indexOf(".") == -1) {
        if(this.current.length==0){
          this.current = "0"+this.current;
        }
        // this.current = this.current + ".";
        this.current=`${this.current}${"."}`
      
      }
    },
    percent() {
      if (this.current != "") {
        this.current = axios
          .get(
            "http://localhost:8083/api/calculator/reminder?Number=" +
              this.current
          )
          .then(response => {
            this.current = response.data;
          });
      }
    },

    Root() {
      this.current = axios
        .get("http://localhost:8083/api/calculator/root?Number=" + this.current)
        .then(response => {
          this.current = response.data;
        });
    },
    OneOverX() {
      this.current = axios
        .get(
          "http://localhost:8083/api/calculator/OneOverX?Number=" + this.current
        )
        .then(response => {
          this.current = response.data;
        });
    },
    Square() {
      this.current = axios
        .get(
          "http://localhost:8083/api/calculator/square?Number=" + this.current
        )
        .then(response => {
          this.current = response.data;
        });
    },
    // to get the corresponding code of an operator to put it in the URL to get the results.
    getOperator(o) {
      if (o == "+") {
        return "%2B";
      } else if (o == "-") {
        return "%2D";
      } else if (o == "*") {
        return "%2A";
      } else if (o == "/") {
        return "%2F";
      }
    },
    equal() {
      this.equalPressed = true;
      if (this.previous == "") {
        this.answer = this.current;
      } else {
        this.calculate(this.operator);
      }
    },
    operation(newOperator) {
      this.equalPressed = false;
      if (this.operatorClicked && this.current != "") {
        this.flag = true;
        this.calculate(newOperator);
      } else if (this.current == "") {
        //To Handle that You can't enter two consecutive operators!
      } else {
        this.operatorClicked = true;
        this.operator = newOperator;
        this.previous = this.current;
        this.current = "";
      }
    },
    calculate(newOperator) {
      if (this.operatorClicked && this.current != "" && this.previous != "") {
        this.answer = axios
          .get(
            "http://localhost:8083/api/calculator/Calculate?firstNumber=" +
              this.previous +
              "&secondNumber=" +
              this.current +
              "&operator=" +
              this.getOperator(this.operator)
              //  ,{ setTimeout: 50000 }
          )
          .then(response => {
            this.answer = response.data;
            if (this.flag) {
              this.operator = newOperator;
              this.previous = response.data;
              this.current = "";
            } else {
              this.current = response.data;
            }
          });
        if (this.equalPressed) {
          this.operator = "";
          this.previous = "";
          this.operatorClicked = false;
          this.flag = false;
        }
      } else if (this.operatorClicked && this.current == "") {
        this.answer = "WHAT ?!";
      }
    }
  }
};
</script>


<style scoped>
.sup {
  position: relative;
  bottom: 0.25ex; 
  font-size: 90%;
}
.calculator {
  display: grid;
  grid-template-rows: repeat(8, minmax(60px, auto));
  grid-template-columns: repeat(4, 60px);
  grid-gap: 12px;
  padding: 35px;
  font-family: "Poppins";
  font-weight: 300;
  font-size: 25px;
  background-color: #ffffff;
  border-radius: 10px;
  box-shadow: 0px 3px 80px -30px rgba(13, 81, 134, 1);
}

.btn {
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  text-align: center;
  text-decoration: none;
  outline: none;
  color: #484848;
  background-color: #f4faff;
  border-radius: 5px;
  transition-duration: 0.1s;
}
.btn:active {
  background-color: rgb(229, 249, 255);
  box-shadow: 0 4px #666;
  transform: translateY(1px);
  /* transform: translateX(1px); */
}
.btn:hover {
  background-color: rgb(63, 193, 236);
  color: rgb(255, 255, 255);
}

.display,
.answer {
  grid-column: 1 / 5;
  display: flex;
  align-items: center;
}

.display {
  color: #a3a3a3;
  border-bottom: 1px solid #e1e1e1;
  margin-bottom: 15px;
  overflow: hidden;
  text-overflow: clip;
  word-wrap: anywhere;

}

.answer {
  font-weight: 500;
  color: #146080;
  font-size: 40px;
  height: 65px;
  word-wrap: anywhere;
}

.operator {
  background-color: #d9efff;
  color: #3fa9fc;
}
</style>

