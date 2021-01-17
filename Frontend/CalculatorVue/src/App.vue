<template>
  <div id="app">
    <Calculator />
  </div>
</template>

<script>
import Calculator from "./components/Calculator.vue";

export default {
  name: "app",
  components: {
    Calculator
  }
};
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  /* text-align: center;
  color: #2c3e50;
  margin-top: 60px; */
  display: flex;
  align-items: center;
  justify-content: center;
  height: 85vh;
  width: 100vw;
}
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  background-color: rgb(29, 30, 34);
}
h1 {
  text-align: center;
  color: white;
  font-size: 30pt;
  border: 5px solid;
  border-color: white;
  margin: 20px;
  padding: 10px;
  width: 400px;
  margin-left: auto;
  margin-right: auto;
}
</style>
