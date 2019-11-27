package MyCore
import chisel3.iotesters.PeekPokeTester

class Top_tb (c:Top) extends PeekPokeTester(c){

    step(1000)

}