package MyCore
import chisel3.iotesters.PeekPokeTester

class DataMem_tb (c:DataMem) extends  PeekPokeTester(c){
    step(1)
}