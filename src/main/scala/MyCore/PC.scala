package MyCore
import chisel3._

class PC extends Module {
    val io = IO(new Bundle {
        val in = Input(UInt(32.W))
        val out = Output(UInt(32.W))
    })

    val reg = RegInit(0.U(32.W))
    reg := io.in
    io.out := reg
}