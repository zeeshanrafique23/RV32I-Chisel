package MyCore
import chisel3._

class ControlDecode extends Module{
    val io = IO(new Bundle{
        val rtype = Input(UInt())
        val itype = Input(UInt())
        val load  = Input(UInt())
        val stype = Input(UInt())
        val sbtype= Input(UInt())
        val lui   = Input(UInt())
        val auipc = Input(UInt())
        val ujtype= Input(UInt())
        val jalr  = Input(UInt())

        val regWrite = Output(UInt(1.W))
        val memtoReg = Output(UInt(1.W))
        val memRead  = Output(UInt(1.W))
        val memWrite = Output(UInt(1.W))
        val branch   = Output(UInt(1.W))
        val oprndAsel= Output(UInt(2.W))
        val oprndBsel= Output(UInt(1.W))
        val immSel   = Output(UInt(2.W))
        val nextPCsel= Output(UInt(3.W))
        val aluOP    = Output(UInt(3.W))
    })

    io.regWrite := 0.U 
    io.memtoReg := 0.U 
    io.memRead  := 0.U 
    io.memWrite := 0.U 
    io.branch   := 0.U 
    io.oprndAsel:= 0.U 
    io.oprndBsel:= 0.U 
    io.immSel   := 0.U 
    io.nextPCsel:= 4.U 
    io.aluOP    := 0.U  

  when (io.rtype === 1.U){
    io.regWrite := 1.U 

}.elsewhen(io.itype === 1.U){
    io.regWrite := 1.U 
    io.oprndBsel:= 1.U 
    io.aluOP    := 1.U  

}.elsewhen(io.load === 1.U){
    io.regWrite := 1.U 
    io.memtoReg := 1.U 
    io.memRead  := 1.U   
    io.oprndBsel:= 1.U  
    io.aluOP    := 4.U

}.elsewhen(io.stype === 1.U){
    io.memWrite := 1.U  
    io.oprndBsel:= 1.U 
    io.immSel   := 1.U 
    io.aluOP    := 4.U

}.elsewhen(io.sbtype === 1.U){
    io.branch   := 1.U  
    io.nextPCsel:= 1.U 
    io.aluOP    := 2.U  

}.elsewhen(io.lui === 1.U){
    io.regWrite := 1.U  
    io.oprndBsel:= 1.U 
    io.immSel   := 2.U  
    io.aluOP    := 4.U

}.elsewhen(io.auipc === 1.U){
    io.regWrite := 1.U  
    io.oprndAsel:= 1.U 
    io.oprndBsel:= 1.U 
    io.immSel   := 2.U 
    io.aluOP    := 4.U

}.elsewhen(io.ujtype === 1.U){
    io.regWrite := 1.U
    io.oprndAsel:= 2.U  
    io.nextPCsel:= 2.U 
    io.aluOP    := 3.U  

}.elsewhen(io.jalr === 1.U){
    io.regWrite := 1.U
    io.oprndAsel:= 2.U  
    io.nextPCsel:= 3.U 
    io.aluOP    := 3.U
}
}