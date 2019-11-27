package MyCore
import chisel3._
import chisel3.util.experimental.loadMemoryFromFile
class InstrucMem extends Module{
    val io = IO(new Bundle{
        val address = Input(UInt(10.W))
        val instruction = Output(UInt(32.W))
    })

    val mem = Mem(1024,UInt(32.W))
  loadMemoryFromFile(mem,"/home/merl/test.txt")
  io.instruction := mem.read(io.address)
}