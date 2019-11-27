package MyCore
import chisel3.iotesters.PeekPokeTester

class PC_tb (c:PC) extends PeekPokeTester(c){
step(1)
}
